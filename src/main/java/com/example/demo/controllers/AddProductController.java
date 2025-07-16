package com.example.demo.controllers;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.service.PartService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AddProductController {

    @Autowired
    private ProductService productService;

    private final PartService partService;

    private static Product productInEdit;

    public AddProductController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping("/showFormAddProduct")
    public String showFormAddProduct(Model model) {
        productInEdit = new Product();
        model.addAttribute("product", productInEdit);
        updatePartLists(model, productInEdit);
        return "productForm";
    }

    @PostMapping("/showFormAddProduct")
    public String saveProduct(@Valid @ModelAttribute("product") Product product,
                              BindingResult bindingResult,
                              Model model) {

        if (productInEdit == null || productInEdit.getParts() == null) {
            productInEdit = productService.findById((int) product.getId());
        }

        product.setParts(productInEdit.getParts());

        if (bindingResult.hasErrors()) {
            updatePartLists(model, product);
            return "productForm";
        }
        for (Part part : product.getParts()) {
            int projectedInventory = part.getInv() - product.getInv();
            if (projectedInventory < part.getMinInv()) {
                model.addAttribute("error", "Adding this product will reduce inventory for part '" +
                        part.getName() + "' below its minimum of " + part.getMinInv() + ".");
                updatePartLists(model, product);
                return "productForm";
            }
        }

        for (Part part : product.getParts()) {
            part.setInv(part.getInv() - product.getInv());
            partService.save(part);
        }

        productService.save(product);

        model.addAttribute("error", null);
        return "redirect:/mainscreen";
    }

    @GetMapping("/showProductFormForUpdate")
    public String showProductFormForUpdate(@RequestParam("productID") int productId, Model model) {
        productInEdit = productService.findById(productId);
        model.addAttribute("product", productInEdit);
        updatePartLists(model, productInEdit);
        return "productForm";
    }

    @GetMapping("/deleteproduct")
    public String deleteProduct(@RequestParam("productID") int productId) {
        Product product = productService.findById(productId);
        for (Part part : product.getParts()) {
            part.getProducts().remove(product);
            partService.save(part);
        }

        product.getParts().clear();
        productService.save(product);
        productService.deleteById(productId);
        return "confirmationdeleteproduct";
    }

    @GetMapping("/associatepart")
    public String associatePart(@RequestParam("partID") int partId, Model model) {
        Part part = partService.findById(partId);
        if (!productInEdit.getParts().contains(part)) {
            productInEdit.getParts().add(part);
            part.getProducts().add(productInEdit);
            partService.save(part);
        }

        model.addAttribute("product", productInEdit);
        updatePartLists(model, productInEdit);
        return "productForm";
    }

    @GetMapping("/removepart")
    public String removePart(@RequestParam("partID") int partId, Model model) {
        if (productInEdit == null) {
            return "redirect:/mainscreen";
        }

        Part part = partService.findById(partId);

        productInEdit.getParts().remove(part); // remove from product
        part.getProducts().remove(productInEdit); // remove from part

        model.addAttribute("product", productInEdit);
        updatePartLists(model, productInEdit);

        return "productForm";
    }

    private void updatePartLists(Model model, Product product) {
        List<Part> allParts = partService.findAll();
        List<Part> availableParts = new ArrayList<>();

        for (Part part : allParts) {
            if (!product.getParts().contains(part)) {
                availableParts.add(part);
            }
        }

        model.addAttribute("parts", allParts);
        model.addAttribute("availparts", availableParts);
        model.addAttribute("assparts", product.getParts());
    }
}
