package com.pirtol.lab.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.pirtol.lab.web.rest.TestUtil;

public class QuartierDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuartierDTO.class);
        QuartierDTO quartierDTO1 = new QuartierDTO();
        quartierDTO1.setId(1L);
        QuartierDTO quartierDTO2 = new QuartierDTO();
        assertThat(quartierDTO1).isNotEqualTo(quartierDTO2);
        quartierDTO2.setId(quartierDTO1.getId());
        assertThat(quartierDTO1).isEqualTo(quartierDTO2);
        quartierDTO2.setId(2L);
        assertThat(quartierDTO1).isNotEqualTo(quartierDTO2);
        quartierDTO1.setId(null);
        assertThat(quartierDTO1).isNotEqualTo(quartierDTO2);
    }
}
