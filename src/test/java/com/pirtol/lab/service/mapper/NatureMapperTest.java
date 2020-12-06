package com.pirtol.lab.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NatureMapperTest {
    private NatureMapper natureMapper;

    @BeforeEach
    public void setUp() {
        natureMapper = new NatureMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(natureMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(natureMapper.fromId(null)).isNull();
    }
}
