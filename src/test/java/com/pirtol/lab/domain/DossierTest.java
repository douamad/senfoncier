package com.pirtol.lab.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.pirtol.lab.web.rest.TestUtil;

public class DossierTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Dossier.class);
        Dossier dossier1 = new Dossier();
        dossier1.setId(1L);
        Dossier dossier2 = new Dossier();
        dossier2.setId(dossier1.getId());
        assertThat(dossier1).isEqualTo(dossier2);
        dossier2.setId(2L);
        assertThat(dossier1).isNotEqualTo(dossier2);
        dossier1.setId(null);
        assertThat(dossier1).isNotEqualTo(dossier2);
    }
}
