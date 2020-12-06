package com.pirtol.lab.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CommuneMapperTest {

    private CommuneMapper communeMapper;

    @BeforeEach
    public void setUp() {
        communeMapper = new CommuneMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(communeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(communeMapper.fromId(null)).isNull();
    }
}
