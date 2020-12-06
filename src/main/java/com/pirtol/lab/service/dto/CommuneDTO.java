package com.pirtol.lab.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.pirtol.lab.domain.Commune} entity.
 */
public class CommuneDTO implements Serializable {
    
    private Long id;

    private String code;

    private String libelle;


    private Long arrondissementId;
    
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

    public Long getArrondissementId() {
        return arrondissementId;
    }

    public void setArrondissementId(Long arrondissementId) {
        this.arrondissementId = arrondissementId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommuneDTO)) {
            return false;
        }

        return id != null && id.equals(((CommuneDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommuneDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", arrondissementId=" + getArrondissementId() +
            "}";
    }
}
