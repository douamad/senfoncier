package com.pirtol.lab.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.pirtol.lab.web.rest.TestUtil;

public class CategorieClotureDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategorieClotureDTO.class);
        CategorieClotureDTO categorieClotureDTO1 = new CategorieClotureDTO();
        categorieClotureDTO1.setId(1L);
        CategorieClotureDTO categorieClotureDTO2 = new CategorieClotureDTO();
        assertThat(categorieClotureDTO1).isNotEqualTo(categorieClotureDTO2);
        categorieClotureDTO2.setId(categorieClotureDTO1.getId());
        assertThat(categorieClotureDTO1).isEqualTo(categorieClotureDTO2);
        categorieClotureDTO2.setId(2L);
        assertThat(categorieClotureDTO1).isNotEqualTo(categorieClotureDTO2);
        categorieClotureDTO1.setId(null);
        assertThat(categorieClotureDTO1).isNotEqualTo(categorieClotureDTO2);
    }
}
