package com.pirtol.lab.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.pirtol.lab.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class UsageDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UsageDTO.class);
        UsageDTO usageDTO1 = new UsageDTO();
        usageDTO1.setId(1L);
        UsageDTO usageDTO2 = new UsageDTO();
        assertThat(usageDTO1).isNotEqualTo(usageDTO2);
        usageDTO2.setId(usageDTO1.getId());
        assertThat(usageDTO1).isEqualTo(usageDTO2);
        usageDTO2.setId(2L);
        assertThat(usageDTO1).isNotEqualTo(usageDTO2);
        usageDTO1.setId(null);
        assertThat(usageDTO1).isNotEqualTo(usageDTO2);
    }
}
