package com.example.demo.service;

import com.example.demo.domain.Part;
import com.example.demo.repositories.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;

    @Autowired
    public PartServiceImpl(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Override
    public List<Part> findAll() {
        return StreamSupport
                .stream(partRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Part> listAll(String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return StreamSupport
                    .stream(partRepository.search(keyword).spliterator(), false)
                    .collect(Collectors.toList());
        }
        return findAll();
    }

    @Override
    public Part findById(int theId) {
        return partRepository.findById((long) theId)
                .orElseThrow(() -> new RuntimeException("Did not find part id - " + theId));
    }

    @Override
    public void save(Part thePart) {
        partRepository.save(thePart);
    }

    @Override
    public void deleteById(int theId) {
        partRepository.deleteById((long) theId);
    }

    @Override
    public List<Part> getAllParts() {
        return findAll();
    }
}
