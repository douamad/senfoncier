package com.pirtol.lab.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CategorieClotureMapperTest {

    private CategorieClotureMapper categorieClotureMapper;

    @BeforeEach
    public void setUp() {
        categorieClotureMapper = new CategorieClotureMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(categorieClotureMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(categorieClotureMapper.fromId(null)).isNull();
    }
}
