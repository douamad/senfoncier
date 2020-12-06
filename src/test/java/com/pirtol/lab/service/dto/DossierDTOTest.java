package com.pirtol.lab.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.pirtol.lab.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class DossierDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DossierDTO.class);
        DossierDTO dossierDTO1 = new DossierDTO();
        dossierDTO1.setId(1L);
        DossierDTO dossierDTO2 = new DossierDTO();
        assertThat(dossierDTO1).isNotEqualTo(dossierDTO2);
        dossierDTO2.setId(dossierDTO1.getId());
        assertThat(dossierDTO1).isEqualTo(dossierDTO2);
        dossierDTO2.setId(2L);
        assertThat(dossierDTO1).isNotEqualTo(dossierDTO2);
        dossierDTO1.setId(null);
        assertThat(dossierDTO1).isNotEqualTo(dossierDTO2);
    }
}
