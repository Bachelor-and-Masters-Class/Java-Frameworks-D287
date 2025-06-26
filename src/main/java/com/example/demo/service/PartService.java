package com.example.demo.service;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;

import java.util.List;

/**
 *
 *
 *
 *
 */
public interface PartService {
    List<Part> findAll();
    Part findById(int theId);
    void save(Part thePart);
    void deleteById(int theId);
    List<Part> listAll(String keyword);
    List<Part> getAllParts();
}
