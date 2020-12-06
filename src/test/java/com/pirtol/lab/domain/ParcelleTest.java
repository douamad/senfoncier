package com.pirtol.lab.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.pirtol.lab.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ParcelleTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Parcelle.class);
        Parcelle parcelle1 = new Parcelle();
        parcelle1.setId(1L);
        Parcelle parcelle2 = new Parcelle();
        parcelle2.setId(parcelle1.getId());
        assertThat(parcelle1).isEqualTo(parcelle2);
        parcelle2.setId(2L);
        assertThat(parcelle1).isNotEqualTo(parcelle2);
        parcelle1.setId(null);
        assertThat(parcelle1).isNotEqualTo(parcelle2);
    }
}
