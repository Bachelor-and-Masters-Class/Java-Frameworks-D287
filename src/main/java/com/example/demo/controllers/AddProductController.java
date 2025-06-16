package com.example.demo.controllers;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.service.PartService;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
    private ApplicationContext context;

    private final PartService partService;
    private static Product productInEdit; // Used across requests for associating/removing parts

    public AddProductController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping("/showFormAddProduct")
    public String showFormAddProduct(Model model) {
        productInEdit = new Product(); // new blank product
        model.addAttribute("product", productInEdit);
        updatePartLists(model, productInEdit);
        return "productForm";
    }

    @PostMapping("/showFormAddProduct")
    public String submitForm(@Valid @ModelAttribute("product") Product product,
                             BindingResult bindingResult,
                             Model model) {

        if (bindingResult.hasErrors()) {
            updatePartLists(model, product);
            return "productForm";
        }

        ProductService productService = context.getBean(ProductServiceImpl.class);

        product.setParts(productInEdit.getParts());

        if (product.getId() != 0) {
            Product originalProduct = productService.findById(product.getId());
            int diff = product.getInv() - originalProduct.getInv();

            if (diff > 0) {
                for (Part p : originalProduct.getParts()) {
                    int resultingInventory = p.getInv() - diff;
                    if (resultingInventory < p.getMin()) {
                        model.addAttribute("error", "Adding this product will reduce inventory for part '" + p.getName() + "' below its minimum (" + p.getMin() + ").");
                        updatePartLists(model, product);
                        return "productForm";
                    }
                }
                for (Part p : originalProduct.getParts()) {
                    p.setInv(p.getInv() - diff);
                    partService.save(p);
                }
            }
        } else {
            // New product, nothing to subtract
            product.setInv(0);
        }

        productService.save(product);
        return "confirmationaddproduct";
    }

    @GetMapping("/showProductFormForUpdate")
    public String showProductFormForUpdate(@RequestParam("productID") int productId, Model model) {
        ProductService productService = context.getBean(ProductServiceImpl.class);
        Product existingProduct = productService.findById((long) productId);
        productInEdit = existingProduct;

        model.addAttribute("product", existingProduct);
        updatePartLists(model, existingProduct);
        return "productForm";
    }

    @GetMapping("/deleteproduct")
    public String deleteProduct(@RequestParam("productID") int productId, Model model) {
        ProductService productService = context.getBean(ProductServiceImpl.class);
        Product product = productService.findById((long) productId);

        for (Part part : product.getParts()) {
            part.getProducts().remove(product);
            partService.save(part);
        }

        product.getParts().clear();
        productService.save(product);
        productService.deleteById((long) productId);
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
        Part part = partService.findById(partId);
        productInEdit.getParts().remove(part);
        part.getProducts().remove(productInEdit);
        partService.save(part);

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

