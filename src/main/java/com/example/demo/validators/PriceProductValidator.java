package com.example.demo.validators;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PriceProductValidator implements ConstraintValidator<ValidProductPrice, Product> {

    @Override
    public void initialize(ValidProductPrice constraintAnnotation) {
        // No initialization needed
    }

    @Override
    public boolean isValid(Product product, ConstraintValidatorContext context) {
        if (product == null || product.getParts() == null) {
            return true;
        }

        double totalPartsPrice = product.getParts().stream()
                .mapToDouble(Part::getPrice)
                .sum();

        return product.getPrice() >= totalPartsPrice;
    }
}
