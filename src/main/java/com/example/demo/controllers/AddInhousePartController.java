package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.service.InhousePartService;
import com.example.demo.service.InhousePartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AddInhousePartController {
    @Autowired
    private ApplicationContext context;

    @GetMapping("/showFormAddInPart")
    public String showFormAddInhousePart(Model theModel) {
        InhousePart inhousepart = new InhousePart();
        theModel.addAttribute("inhousepart", inhousepart);
        return "InhousePartForm";
    }

    @PostMapping("/showFormAddInPart")
    public String submitForm(@Valid @ModelAttribute("inhousepart") InhousePart part,
                             BindingResult theBindingResult,
                             Model theModel) {

        // Custom validation messages
        if (part.getInv() < part.getMinInv()) {
            theBindingResult.rejectValue("inv", "error.inv", "Inventory cannot be less than the minimum.");
        } else if (part.getInv() > part.getMaxInv()) {
            theBindingResult.rejectValue("inv", "error.inv", "Inventory cannot be greater than the maximum.");
        }

        theModel.addAttribute("inhousepart", part);

        if (theBindingResult.hasErrors()) {
            return "InhousePartForm";
        } else {
            InhousePartService repo = context.getBean(InhousePartServiceImpl.class);
            InhousePart existing = repo.findById(part.getId().intValue());
            if (existing != null) part.setProducts(existing.getProducts());
            repo.save(part);

            return "confirmationaddpart";
        }
    }
}

