package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Product;
import com.example.demo.repositories.InhousePartRepository;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class BootStrapData implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final InhousePartRepository inhousePartRepository;
    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(ProductRepository productRepository,
                         OutsourcedPartRepository outsourcedPartRepository,
                         InhousePartRepository inhousePartRepository) {
        this.productRepository = productRepository;
        this.outsourcedPartRepository = outsourcedPartRepository;
        this.inhousePartRepository = inhousePartRepository;
    }

    @Override
    public void run(String... args) {

        productRepository.deleteAll();
        inhousePartRepository.deleteAll();
        outsourcedPartRepository.deleteAll();

            InhousePart drill = new InhousePart("Cordless Drill", 49.99, 50, 5, 60);
            InhousePart stripper = new InhousePart("Wire Stripper", 12.50, 40, 4, 50);
            InhousePart wrench = new InhousePart("Pipe Wrench", 24.75, 30, 3, 40);
            InhousePart knife = new InhousePart("Utility Knife", 8.75, 30, 5, 50);
            InhousePart spanner = new InhousePart("Adjustable Spanner", 14.00, 25, 5, 40);
            inhousePartRepository.saveAll(Arrays.asList(drill, stripper, wrench, knife, spanner));

            OutsourcedPart socketSet = new OutsourcedPart("Socket Set", 19.99, 40, 2, 50, "ToolPro Inc.");
            OutsourcedPart allenSet = new OutsourcedPart("Allen Wrench Set", 11.49, 45, 4, 60, "FixIt Tools");
            outsourcedPartRepository.saveAll(Arrays.asList(socketSet, allenSet));

            Product repairKit = new Product("Deluxe Home Repair Kit", 99.99, 5);
            Product electricianKit = new Product("Electrician Tool Set", 89.50, 3);
            Product plumbingKit = new Product("Plumbing Repair Kit", 74.25, 4);
            Product drillKit = new Product("Cordless Drill Kit", 79.99, 6);
            Product generalRepair = new Product("General Repair Set", 82.00, 2);

            repairKit.getParts().addAll(Arrays.asList(drill, knife, spanner));
            electricianKit.getParts().addAll(Arrays.asList(stripper, socketSet, allenSet));
            plumbingKit.getParts().addAll(Arrays.asList(wrench, spanner));
            drillKit.getParts().addAll(Arrays.asList(drill, socketSet));
            generalRepair.getParts().addAll(Arrays.asList(wrench, knife, allenSet));

            productRepository.saveAll(Arrays.asList(repairKit, electricianKit, plumbingKit, drillKit, generalRepair));
        }
    }