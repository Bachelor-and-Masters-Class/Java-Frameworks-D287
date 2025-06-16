package com.example.demo.bootstrap;

import com.example.demo.DemoApplication;
import com.example.demo.domain.*;
import com.example.demo.repositories.InhousePartRepository;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class BootStrapData implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final OutsourcedPartRepository outsourcedPartRepository;
    private final InhousePartRepository inhousePartRepository;

    public BootStrapData(
            PartRepository partRepository,
            ProductRepository productRepository,
            OutsourcedPartRepository outsourcedPartRepository,
            InhousePartRepository inhousePartRepository
    ) {
        this.productRepository = productRepository;
        this.outsourcedPartRepository = outsourcedPartRepository;
        this.inhousePartRepository = inhousePartRepository;
    }

    public void run(Class<DemoApplication> demoApplicationClass, String... args) {
        // Inhouse Parts
        InhousePart ip1 = new InhousePart();
        ip1.setId(201L);
        ip1.setName("Hammer");
        ip1.setPrice(12.99);
        ip1.setInv(15);
        ip1.setMinInv(5);
        ip1.setMaxInv(50);
        inhousePartRepository.save(ip1);

        InhousePart ip2 = new InhousePart();
        ip2.setId(202L);
        ip2.setName("Wrench");
        ip2.setPrice(9.49);
        ip2.setInv(20);
        ip2.setMinInv(5);
        ip2.setMaxInv(50);
        inhousePartRepository.save(ip2);

        InhousePart ip3 = new InhousePart();
        ip3.setId(203L);
        ip3.setName("Pliers");
        ip3.setPrice(6.75);
        ip3.setInv(18);
        ip3.setMinInv(3);
        ip3.setMaxInv(60);
        inhousePartRepository.save(ip3);

        InhousePart ip4 = new InhousePart();
        ip4.setId(204L);
        ip4.setName("Screwdriver");
        ip4.setPrice(5.99);
        ip4.setInv(30);
        ip4.setMinInv(5);
        ip4.setMaxInv(75);
        inhousePartRepository.save(ip4);

        InhousePart ip5 = new InhousePart();
        ip5.setId(205L);
        ip5.setName("Drill Bit Set");
        ip5.setPrice(14.99);
        ip5.setInv(10);
        ip5.setMinInv(2);
        ip5.setMaxInv(40);
        inhousePartRepository.save(ip5);

        List<InhousePart> inhouseParts = (List<InhousePart>) inhousePartRepository.findAll();
        for (InhousePart part : inhouseParts) {
            System.out.println(part.getName() + " " + part.getId());
        }

        // Outsourced Parts
        OutsourcedPart op1 = new OutsourcedPart();
        op1.setCompanyName("ToolPro Inc.");
        op1.setName("Socket Set");
        op1.setInv(12);
        op1.setPrice(19.99);
        op1.setId(301L);
        op1.setMinInv(2);
        op1.setMaxInv(50);
        outsourcedPartRepository.save(op1);

        OutsourcedPart op2 = new OutsourcedPart();
        op2.setCompanyName("FixIt Tools");
        op2.setName("Allen Wrench Set");
        op2.setInv(22);
        op2.setPrice(11.49);
        op2.setId(302L);
        op2.setMinInv(4);
        op2.setMaxInv(60);
        outsourcedPartRepository.save(op2);

        List<OutsourcedPart> outsourcedParts = (List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for (OutsourcedPart part : outsourcedParts) {
            System.out.println(part.getName() + " " + part.getCompanyName());
        }

        Product repairKit = new Product("Home Repair Kit", 39.99, 5, 1, 10);
        Product plumbingKit = new Product("Plumbing Kit", 29.99, 4, 1, 8);
        Product drillKit = new Product("Drill Kit", 49.99, 3, 1, 6);
        Product electricianKit = new Product("Electrician Starter Kit", 44.99, 2, 1, 5);
        Product multipackTools = new Product("Multi-Pack Tool Set", 59.99, 3, 1, 7);

        productRepository.save(repairKit);
        productRepository.save(plumbingKit);
        productRepository.save(drillKit);
        productRepository.save(electricianKit);
        productRepository.save(multipackTools);
    }
}
