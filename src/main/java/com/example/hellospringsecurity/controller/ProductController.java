package com.example.hellospringsecurity.controller;
import com.example.hellospringsecurity.Entity.Product;
import com.example.hellospringsecurity.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/products")
public class ProductController {
    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "products/add_product"; // This should be the name of your add form view
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/api/products";
    }

    @GetMapping()
    public String getAllProduct(Model model) {
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products", products);
        return "products/products"; // Update this line
    }
    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Integer id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "products/edit_product"; // This should be the name of your edit form view
    }
    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Integer id, @ModelAttribute Product products) {
        products.setId(id);
        productService.updateProduct(products);
        return "redirect:/api/products";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@ModelAttribute Product product) {
        productService.deleteProduct(product.getId());
        return "redirect:/api/products";
    }
}

