package com.pirtol.lab.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrondissementMapperTest {
    private ArrondissementMapper arrondissementMapper;

    @BeforeEach
    public void setUp() {
        arrondissementMapper = new ArrondissementMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(arrondissementMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(arrondissementMapper.fromId(null)).isNull();
    }
}
