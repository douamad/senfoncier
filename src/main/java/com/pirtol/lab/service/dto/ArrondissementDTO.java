package com.pirtol.lab.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.pirtol.lab.domain.Arrondissement} entity.
 */
public class ArrondissementDTO implements Serializable {
    private Long id;

    private String code;

    private String libelle;

    private Long departementId;

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

    public Long getDepartementId() {
        return departementId;
    }

    public void setDepartementId(Long departementId) {
        this.departementId = departementId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArrondissementDTO)) {
            return false;
        }

        return id != null && id.equals(((ArrondissementDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ArrondissementDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", departementId=" + getDepartementId() +
            "}";
    }
}
