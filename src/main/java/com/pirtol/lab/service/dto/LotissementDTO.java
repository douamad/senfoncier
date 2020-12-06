package com.pirtol.lab.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.pirtol.lab.domain.Lotissement} entity.
 */
public class LotissementDTO implements Serializable {
    private Long id;

    private String code;

    private String libelle;

    private Long quartierId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Long getQuartierId() {
        return quartierId;
    }

    public void setQuartierId(Long quartierId) {
        this.quartierId = quartierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LotissementDTO)) {
            return false;
        }

        return id != null && id.equals(((LotissementDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LotissementDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", quartierId=" + getQuartierId() +
            "}";
    }
}
