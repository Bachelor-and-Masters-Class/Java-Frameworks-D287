package com.example.demo.validators;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnufPartsValidator implements ConstraintValidator<ValidEnufParts, Product> {

    @Override
    public void initialize(ValidEnufParts constraintAnnotation) {

    }

    @Override
    public boolean isValid(Product product, ConstraintValidatorContext context) {

        if (product.getParts() == null || product.getParts().isEmpty()) return true;


        for (Part part : product.getParts()) {
            if (part.getInv() <= 0) {
                return false;
            }
        }

        return true;
    }
}
