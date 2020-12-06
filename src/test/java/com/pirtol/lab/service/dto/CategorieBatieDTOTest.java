package com.pirtol.lab.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.pirtol.lab.web.rest.TestUtil;

public class CategorieBatieDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategorieBatieDTO.class);
        CategorieBatieDTO categorieBatieDTO1 = new CategorieBatieDTO();
        categorieBatieDTO1.setId(1L);
        CategorieBatieDTO categorieBatieDTO2 = new CategorieBatieDTO();
        assertThat(categorieBatieDTO1).isNotEqualTo(categorieBatieDTO2);
        categorieBatieDTO2.setId(categorieBatieDTO1.getId());
        assertThat(categorieBatieDTO1).isEqualTo(categorieBatieDTO2);
        categorieBatieDTO2.setId(2L);
        assertThat(categorieBatieDTO1).isNotEqualTo(categorieBatieDTO2);
        categorieBatieDTO1.setId(null);
        assertThat(categorieBatieDTO1).isNotEqualTo(categorieBatieDTO2);
    }
}
