package com.pirtol.lab.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuartierMapperTest {
    private QuartierMapper quartierMapper;

    @BeforeEach
    public void setUp() {
        quartierMapper = new QuartierMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(quartierMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(quartierMapper.fromId(null)).isNull();
    }
}
