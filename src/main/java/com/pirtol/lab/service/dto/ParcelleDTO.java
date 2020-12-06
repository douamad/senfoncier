package com.pirtol.lab.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.pirtol.lab.domain.Parcelle} entity.
 */
public class ParcelleDTO implements Serializable {
    
    private Long id;

    private String numSection;

    private String numeroParcelle;

    private String nicad;

    private Double superfici;

    private String titreMere;

    private String titreCree;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumSection() {
        return numSection;
    }

    public void setNumSection(String numSection) {
        this.numSection = numSection;
    }

    public String getNumeroParcelle() {
        return numeroParcelle;
    }

    public void setNumeroParcelle(String numeroParcelle) {
        this.numeroParcelle = numeroParcelle;
    }

    public String getNicad() {
        return nicad;
    }

    public void setNicad(String nicad) {
        this.nicad = nicad;
    }

    public Double getSuperfici() {
        return superfici;
    }

    public void setSuperfici(Double superfici) {
        this.superfici = superfici;
    }

    public String getTitreMere() {
        return titreMere;
    }

    public void setTitreMere(String titreMere) {
        this.titreMere = titreMere;
    }

    public String getTitreCree() {
        return titreCree;
    }

    public void setTitreCree(String titreCree) {
        this.titreCree = titreCree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ParcelleDTO)) {
            return false;
        }

        return id != null && id.equals(((ParcelleDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ParcelleDTO{" +
            "id=" + getId() +
            ", numSection='" + getNumSection() + "'" +
            ", numeroParcelle='" + getNumeroParcelle() + "'" +
            ", nicad='" + getNicad() + "'" +
            ", superfici=" + getSuperfici() +
            ", titreMere='" + getTitreMere() + "'" +
            ", titreCree='" + getTitreCree() + "'" +
            "}";
    }
}
