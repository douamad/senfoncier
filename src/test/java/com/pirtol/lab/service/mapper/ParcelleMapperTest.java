package com.pirtol.lab.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ParcelleMapperTest {

    private ParcelleMapper parcelleMapper;

    @BeforeEach
    public void setUp() {
        parcelleMapper = new ParcelleMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(parcelleMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(parcelleMapper.fromId(null)).isNull();
    }
}
