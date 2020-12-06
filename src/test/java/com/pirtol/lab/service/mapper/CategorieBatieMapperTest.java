package com.pirtol.lab.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CategorieBatieMapperTest {
    private CategorieBatieMapper categorieBatieMapper;

    @BeforeEach
    public void setUp() {
        categorieBatieMapper = new CategorieBatieMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(categorieBatieMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(categorieBatieMapper.fromId(null)).isNull();
    }
}
