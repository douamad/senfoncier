package com.pirtol.lab.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ActiviteMapperTest {
    private ActiviteMapper activiteMapper;

    @BeforeEach
    public void setUp() {
        activiteMapper = new ActiviteMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(activiteMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(activiteMapper.fromId(null)).isNull();
    }
}
