package com.pirtol.lab.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LotissementMapperTest {
    private LotissementMapper lotissementMapper;

    @BeforeEach
    public void setUp() {
        lotissementMapper = new LotissementMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(lotissementMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(lotissementMapper.fromId(null)).isNull();
    }
}
