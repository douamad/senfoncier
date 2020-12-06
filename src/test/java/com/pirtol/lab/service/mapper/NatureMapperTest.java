package com.pirtol.lab.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

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
