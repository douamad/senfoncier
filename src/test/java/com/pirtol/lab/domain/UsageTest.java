package com.pirtol.lab.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.pirtol.lab.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class UsageTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Usage.class);
        Usage usage1 = new Usage();
        usage1.setId(1L);
        Usage usage2 = new Usage();
        usage2.setId(usage1.getId());
        assertThat(usage1).isEqualTo(usage2);
        usage2.setId(2L);
        assertThat(usage1).isNotEqualTo(usage2);
        usage1.setId(null);
        assertThat(usage1).isNotEqualTo(usage2);
    }
}
