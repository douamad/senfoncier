package com.pirtol.lab.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DossierMapperTest {

    private DossierMapper dossierMapper;

    @BeforeEach
    public void setUp() {
        dossierMapper = new DossierMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dossierMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dossierMapper.fromId(null)).isNull();
    }
}
