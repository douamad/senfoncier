package com.pirtol.lab.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.pirtol.lab.web.rest.TestUtil;

public class CommuneDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommuneDTO.class);
        CommuneDTO communeDTO1 = new CommuneDTO();
        communeDTO1.setId(1L);
        CommuneDTO communeDTO2 = new CommuneDTO();
        assertThat(communeDTO1).isNotEqualTo(communeDTO2);
        communeDTO2.setId(communeDTO1.getId());
        assertThat(communeDTO1).isEqualTo(communeDTO2);
        communeDTO2.setId(2L);
        assertThat(communeDTO1).isNotEqualTo(communeDTO2);
        communeDTO1.setId(null);
        assertThat(communeDTO1).isNotEqualTo(communeDTO2);
    }
}
