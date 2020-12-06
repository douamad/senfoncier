package com.pirtol.lab.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.pirtol.lab.domain.Quartier} entity.
 */
public class QuartierDTO implements Serializable {
    
    private Long id;

    private String code;

    private String libelle;


    private Long commununeId;
    
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

    public Long getCommununeId() {
        return commununeId;
    }

    public void setCommununeId(Long communeId) {
        this.commununeId = communeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuartierDTO)) {
            return false;
        }

        return id != null && id.equals(((QuartierDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuartierDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", commununeId=" + getCommununeId() +
            "}";
    }
}
