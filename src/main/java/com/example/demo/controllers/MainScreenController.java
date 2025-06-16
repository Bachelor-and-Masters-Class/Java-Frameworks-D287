package com.example.demo.controllers;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class MainScreenController {

    private final ProductService productService;

    public MainScreenController(ProductService productService) {
        this.productService = productService;
    }

    // Loads main screen and displays product list (with optional keyword filter)
    @GetMapping({"/", "/mainscreen"})
    public String showMainScreen(Model model,
                                 @RequestParam(value = "productkeyword", required = false) String productkeyword,
                                 @RequestParam(value = "message", required = false) String message) {
        List<Product> productList = productService.listAll(productkeyword);
        model.addAttribute("productList", productList);
        model.addAttribute("productkeyword", productkeyword);
        if (message != null) {
            model.addAttribute("message", message);
        }
        return "mainscreen";
    }

    // Buy Now logic
    @PostMapping("/buy/{id}")
    public String buyProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<Product> productOptional = productService.findById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            if (product.getInv() > 0) {
                product.setInv(product.getInv() - 1);
                productService.save(product);
                redirectAttributes.addFlashAttribute("message", "Purchase successful!");
            } else {
                redirectAttributes.addFlashAttribute("message", "Out of stock.");
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Product not found.");
        }

        return "redirect:/mainscreen";
    }
}
