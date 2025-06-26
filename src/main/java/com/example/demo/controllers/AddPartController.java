package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.repositories.PartRepository;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *
 *
 *
 *
 */
@Controller
public class AddPartController {
    @Autowired
    private ApplicationContext context;

    @GetMapping("/showPartFormForUpdate")
    public String showPartFormForUpdate(@RequestParam("partID") int theId, Model theModel) {

        PartService repo = context.getBean(PartServiceImpl.class);
        OutsourcedPartService outsourcedrepo = context.getBean(OutsourcedPartServiceImpl.class);
        InhousePartService inhouserepo = context.getBean(InhousePartServiceImpl.class);

        boolean inhouse = true;
        List<OutsourcedPart> outsourcedParts = outsourcedrepo.findAll();
        for (OutsourcedPart outsourcedPart : outsourcedParts) {
            if (outsourcedPart.getId() == theId) {
                inhouse = false;
                break;
            }
        }
        String formtype;
        if (inhouse) {
            InhousePart inhousePart = inhouserepo.findById(theId);
            theModel.addAttribute("inhousepart", inhousePart);
            formtype = "InhousePartForm";
        } else {
            OutsourcedPart outsourcedPart = outsourcedrepo.findById(theId);
            theModel.addAttribute("outsourcedpart", outsourcedPart);
            formtype = "OutsourcedPartForm";
        }
        return formtype;
    }

    @GetMapping("/deletepart")
    public String deletePart(@Valid @RequestParam("partID") int theId, Model theModel) {
        PartService repo = context.getBean(PartServiceImpl.class);
        Part part = repo.findById(theId);
        if (part.getProducts().isEmpty()) {
            repo.deleteById(theId);
            return "confirmationdeletepart";
        } else {
            return "negativeerror";
        }
    }

    @PostMapping("/saveInhousePart")
    public String saveInhousePart(@Valid @ModelAttribute("inhousepart") InhousePart part,
                                  BindingResult bindingResult,
                                  Model model) {

        if (!part.isInventoryValid()) {
            bindingResult.rejectValue("inv", "error.inv", "Inventory cannot be less than the minimum.");
        } else if (part.getInv() > part.getMaxInv()) {
            bindingResult.rejectValue("inv", "error.inv", "Inventory cannot be greater than the maximum.");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("inhousepart", part);
            return "InhousePartForm";
        }

        PartService partService = context.getBean(PartServiceImpl.class);
        partService.save(part);

        return "confirmationaddpart";
    }

    // Save OutsourcedPart
    @PostMapping("/saveOutsourcedPart")
    public String saveOutsourcedPart(@Valid @ModelAttribute("outsourcedpart") OutsourcedPart part,
                                     BindingResult bindingResult,
                                     Model model) {

        if (!part.isInventoryValid()) {
            bindingResult.rejectValue("inv", "error.inv", "Inventory cannot be less than the minimum.");
        } else if (part.getInv() > part.getMaxInv()) {
            bindingResult.rejectValue("inv", "error.inv", "Inventory cannot be greater than the maximum.");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("outsourcedpart", part);
            return "OutsourcedPartForm";
        }

        PartService partService = context.getBean(PartServiceImpl.class);
        partService.save(part);

        return "confirmationaddpart";
    }

    @GetMapping("/showFormAddPart")
    public String showFormAddPart(Model model) {

        InhousePart newPart = new InhousePart();
        newPart.setMinInv(1);
        newPart.setMaxInv(10);

        model.addAttribute("inhousepart", newPart);
        return "InhousePartForm";
    }
}
