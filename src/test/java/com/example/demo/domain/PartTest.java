package com.example.demo.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Project: demoDarbyFrameworks2-master
 * Package: com.example.demo.domain
 * <p>
 * User: carolyn.sher
 * Date: 6/24/2022
 * Time: 3:44 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
class PartTest {
    Part partIn;
    Part partOut;

    @BeforeEach
    void setUp() {
        partIn = new InhousePart();
        partOut = new OutsourcedPart();
    }

    @Test
    void getId() {
        Long idValue = 4L;
        partIn.setId((long) Math.toIntExact(idValue));
        assertEquals(idValue, partIn.getId());
        partOut.setId(idValue);
        assertEquals(idValue, partOut.getId());
    }

    @Test
    void setId() {
        Long idValue = 4L;
        partIn.setId(idValue);
        assertEquals(idValue, partIn.getId());
        partOut.setId(idValue);
        assertEquals(idValue, partOut.getId());
    }

    @Test
    void getName() {
        String name = "test inhouse part";
        partIn.setName(name);
        assertEquals(name, partIn.getName());
        name = "test outsourced part";
        partOut.setName(name);
        assertEquals(name, partOut.getName());
    }

    @Test
    void setName() {
        String name = "test inhouse part";
        partIn.setName(name);
        assertEquals(name, partIn.getName());
        name = "test outsourced part";
        partOut.setName(name);
        assertEquals(name, partOut.getName());
    }

    @Test
    void getPrice() {
        double price = 1.0;
        partIn.setPrice(price);
        assertEquals(price, partIn.getPrice());
        partOut.setPrice(price);
        assertEquals(price, partOut.getPrice());
    }

    @Test
    void setPrice() {
        double price = 1.0;
        partIn.setPrice(price);
        assertEquals(price, partIn.getPrice());
        partOut.setPrice(price);
        assertEquals(price, partOut.getPrice());
    }

    @Test
    void getInv() {
        int inv = 5;
        partIn.setInv(inv);
        assertEquals(inv, partIn.getInv());
        partOut.setInv(inv);
        assertEquals(inv, partOut.getInv());
    }

    @Test
    void setInv() {
        int inv = 5;
        partIn.setInv(inv);
        assertEquals(inv, partIn.getInv());
        partOut.setInv(inv);
        assertEquals(inv, partOut.getInv());
    }

    @Test
    void getProducts() {
        Product product1 = new Product();
        Product product2 = new Product();
        Set<Product> myProducts = new HashSet<>();
        myProducts.add(product1);
        myProducts.add(product2);
        partIn.setProducts((List<Product>) myProducts);
        assertEquals(myProducts, partIn.getProducts());
        partOut.setProducts((List<Product>) myProducts);
        assertEquals(myProducts, partOut.getProducts());
    }

    @Test
    void setProducts() {
        Product product1 = new Product();
        Product product2 = new Product();
        Set<Product> myProducts = new HashSet<>();
        myProducts.add(product1);
        myProducts.add(product2);
        partIn.setProducts((List<Product>) myProducts);
        assertEquals(myProducts, partIn.getProducts());
        partOut.setProducts((List<Product>) myProducts);
        assertEquals(myProducts, partOut.getProducts());
    }

    @Test
    void testToString() {
        String name = "test inhouse part";
        partIn.setName(name);
        assertEquals(name, partIn.toString());
        name = "test outsourced part";
        partOut.setName(name);
        assertEquals(name, partOut.toString());
    }

    @Test
    void testEquals() {
        partIn.setId(1L);
        Part newPartIn = new InhousePart();
        newPartIn.setId(1L);
        assertEquals(partIn, newPartIn);
        partOut.setId(1L);
        Part newPartOut = new OutsourcedPart();
        newPartOut.setId(1L);
        assertEquals(partOut, newPartOut);

    }

    @Test
    void testHashCode() {
        partIn.setId(1L);
        partOut.setId(1L);
        assertEquals(partIn.hashCode(), partOut.hashCode());
    }

    @Test
    void testInventoryBelowMinimumIsInvalid() {
        partIn.setMin(5);
        partIn.setMax(15);
        partIn.setInv(3);
        assertFalse(partIn.isInventoryValid(), "Inventory below min should be invalid");
    }

    @Test
    void testInventoryAboveMaximumIsInvalid() {
        partIn.setMin(1);
        partIn.setMax(10);
        partIn.setInv(12);
        assertFalse(partIn.isInventoryValid(), "Inventory above max should be invalid");
    }

    @Test
    void testMinimumInventoryIsSetCorrectly() {
        partIn.setMin(4);
        partIn.setMax(15);
        partIn.setInv(3);
        assertFalse(partIn.isInventoryValid(), "Inventory should be invalid when below the new min");
    }

    @Test
    void testMaximumInventoryIsSetCorrectly() {
        partIn.setMin(2);
        partIn.setMax(6);
        partIn.setInv(7);
        assertFalse(partIn.isInventoryValid(), "Inventory should be invalid when above the new max");
    }
}