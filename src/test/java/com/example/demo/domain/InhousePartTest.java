package com.example.demo.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Project: demoDarbyFrameworks2-master
 * Package: com.example.demo.domain
 * <p>
 * User: carolyn.sher
 * Date: 6/24/2022
 * Time: 3:45 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
class InhousePartTest {
    InhousePart ip;

    @BeforeEach
    void setUp() {
        ip = new InhousePart();
    }

    @Test
    void getIdTest() {
        Long idValue = 4L;
        ip.setId(idValue);
        assertEquals(idValue, ip.getId());
    }

    @Test
    void setIdTest() {
        Long idValue = 4L;
        ip.setId(idValue);
        assertEquals(idValue, ip.getId());
    }
}
