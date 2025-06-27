package com.example.demo.controllers;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.service.PartService;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class MainScreenController {

    private final ProductService productService;
    private final PartService partService;

    public MainScreenController(ProductService productService, PartService partService) {
        this.productService = productService;
        this.partService = partService;
    }

    // Loads the main screen with product list and optional search
    @GetMapping({"/", "/mainscreen"})
    public String showMainScreen(Model model,
                                 @RequestParam(value = "productkeyword", required = false) String productkeyword,
                                 @RequestParam(value = "message", required = false) String message) {
        List<Product> productList = productService.listAll(productkeyword);
        List<Part> partList = partService.getAllParts();

        model.addAttribute("productList", productList);
        model.addAttribute("partList", partList);
        model.addAttribute("productkeyword", productkeyword);
        if (message != null) {
            model.addAttribute("message", message);
        }
        return "mainscreen"; // maps to mainscreen.html
    }

    // "Buy Now" functionality for a product
    @PostMapping("/buy/{id}")
    public String buyProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            Product product = productService.findById(Math.toIntExact(id));

            if (product != null) {
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
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Purchase failed: " + e.getMessage());
        }

        return "redirect:/mainscreen";
    }
}