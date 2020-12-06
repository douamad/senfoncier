package com.pirtol.lab.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ProprietaireMapperTest {

    private ProprietaireMapper proprietaireMapper;

    @BeforeEach
    public void setUp() {
        proprietaireMapper = new ProprietaireMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(proprietaireMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proprietaireMapper.fromId(null)).isNull();
    }
}
