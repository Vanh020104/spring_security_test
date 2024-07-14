package com.example.hellospringsecurity.controller;

import com.example.hellospringsecurity.Entity.AppUser;
import com.example.hellospringsecurity.dto.RegisterDto;
import com.example.hellospringsecurity.repositories.AppUserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class AccountController {
    @Autowired
    private AppUserRepository appUserRepository;
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }
    @GetMapping("/register")
    public String register(Model model) {
        RegisterDto registerDto = new RegisterDto();
        model.addAttribute(registerDto);
        model.addAttribute("success", false);
        return "register";
    }
    @PostMapping("/register")
    public String register(Model model, @Valid @ModelAttribute RegisterDto registerDto, BindingResult bindingResult) {

        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            bindingResult.addError(
                    new FieldError("registerDto", "confirmPassword",
                            "Password does not match")
            );
        }

        AppUser appUser = appUserRepository.findByEmail(registerDto.getEmail());
        if (appUser != null) {
            bindingResult.addError(
                    new FieldError("registerDto", "email",
                            "Email already exists")
            );
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            var bCryptPasswordEncoder = new BCryptPasswordEncoder();
            AppUser newUser = new AppUser();
            newUser.setFirstName(registerDto.getFirstName());
            newUser.setLastName(registerDto.getLastName());
            newUser.setEmail(registerDto.getEmail());
            newUser.setPhone(registerDto.getPhone());
            newUser.setRole("USER");
            newUser.setAddress(registerDto.getAddress());
            newUser.setCreatedAt(new Date());
            newUser.setPassword(bCryptPasswordEncoder.encode(registerDto.getPassword()));
            appUserRepository.save(newUser);

            model.addAttribute("registerDto", new RegisterDto());
            model.addAttribute("success", true);
        } catch (Exception e) {
           bindingResult.addError(
                   new FieldError("registerDto", "firstName",e.getMessage())
           );
        }
        return "register";
    }

}
