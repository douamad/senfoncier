package com.pirtol.lab.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.pirtol.lab.domain.CategorieBatie} entity.
 */
public class CategorieBatieDTO implements Serializable {
    
    private Long id;

    private String libelle;

    private Double prixMetreCare;


    private Long dossierId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Double getPrixMetreCare() {
        return prixMetreCare;
    }

    public void setPrixMetreCare(Double prixMetreCare) {
        this.prixMetreCare = prixMetreCare;
    }

    public Long getDossierId() {
        return dossierId;
    }

    public void setDossierId(Long dossierId) {
        this.dossierId = dossierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategorieBatieDTO)) {
            return false;
        }

        return id != null && id.equals(((CategorieBatieDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CategorieBatieDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", prixMetreCare=" + getPrixMetreCare() +
            ", dossierId=" + getDossierId() +
            "}";
    }
}
