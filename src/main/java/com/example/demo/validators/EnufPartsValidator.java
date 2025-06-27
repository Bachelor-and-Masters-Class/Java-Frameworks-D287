package com.example.demo.validators;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnufPartsValidator implements ConstraintValidator<ValidEnufParts, Product> {

    @Override
    public boolean isValid(Product product, ConstraintValidatorContext context) {
        if (product == null || product.getParts() == null || product.getParts().isEmpty()) {
            return true; // No parts associated = skip this check
        }

        int requiredQuantity = product.getInv();

        for (Part part : product.getParts()) {
            if (part.getInv() < requiredQuantity) {
                return false;
            }
        }

        return true;
    }
}

