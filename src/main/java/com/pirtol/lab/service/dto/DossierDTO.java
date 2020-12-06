package com.pirtol.lab.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link com.pirtol.lab.domain.Dossier} entity.
 */
public class DossierDTO implements Serializable {
    
    private Long id;

    private String numero;

    private Double montantLoyer;

    private Double superficieBatie;

    private Double coeffCorrectifBatie;

    private Double valeurBatie;

    private Double lineaireCloture;

    private Double coeffCloture;

    private Double valeurCloture;

    private String amenagementSpaciaux;

    private Double valeurAmenagement;

    private Double valeurVenale;

    private String valeurLocativ;

    private String paysAdresse;

    private String villeAdresse;

    private Set<ParcelleDTO> parcelles = new HashSet<>();

    private Long dossierId;

    private Long natureId;

    private Long activiteId;

    private Long usageId;

    private Long proprietaireId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Double getMontantLoyer() {
        return montantLoyer;
    }

    public void setMontantLoyer(Double montantLoyer) {
        this.montantLoyer = montantLoyer;
    }

    public Double getSuperficieBatie() {
        return superficieBatie;
    }

    public void setSuperficieBatie(Double superficieBatie) {
        this.superficieBatie = superficieBatie;
    }

    public Double getCoeffCorrectifBatie() {
        return coeffCorrectifBatie;
    }

    public void setCoeffCorrectifBatie(Double coeffCorrectifBatie) {
        this.coeffCorrectifBatie = coeffCorrectifBatie;
    }

    public Double getValeurBatie() {
        return valeurBatie;
    }

    public void setValeurBatie(Double valeurBatie) {
        this.valeurBatie = valeurBatie;
    }

    public Double getLineaireCloture() {
        return lineaireCloture;
    }

    public void setLineaireCloture(Double lineaireCloture) {
        this.lineaireCloture = lineaireCloture;
    }

    public Double getCoeffCloture() {
        return coeffCloture;
    }

    public void setCoeffCloture(Double coeffCloture) {
        this.coeffCloture = coeffCloture;
    }

    public Double getValeurCloture() {
        return valeurCloture;
    }

    public void setValeurCloture(Double valeurCloture) {
        this.valeurCloture = valeurCloture;
    }

    public String getAmenagementSpaciaux() {
        return amenagementSpaciaux;
    }

    public void setAmenagementSpaciaux(String amenagementSpaciaux) {
        this.amenagementSpaciaux = amenagementSpaciaux;
    }

    public Double getValeurAmenagement() {
        return valeurAmenagement;
    }

    public void setValeurAmenagement(Double valeurAmenagement) {
        this.valeurAmenagement = valeurAmenagement;
    }

    public Double getValeurVenale() {
        return valeurVenale;
    }

    public void setValeurVenale(Double valeurVenale) {
        this.valeurVenale = valeurVenale;
    }

    public String getValeurLocativ() {
        return valeurLocativ;
    }

    public void setValeurLocativ(String valeurLocativ) {
        this.valeurLocativ = valeurLocativ;
    }

    public String getPaysAdresse() {
        return paysAdresse;
    }

    public void setPaysAdresse(String paysAdresse) {
        this.paysAdresse = paysAdresse;
    }

    public String getVilleAdresse() {
        return villeAdresse;
    }

    public void setVilleAdresse(String villeAdresse) {
        this.villeAdresse = villeAdresse;
    }

    public Set<ParcelleDTO> getParcelles() {
        return parcelles;
    }

    public void setParcelles(Set<ParcelleDTO> parcelles) {
        this.parcelles = parcelles;
    }

    public Long getDossierId() {
        return dossierId;
    }

    public void setDossierId(Long lotissementId) {
        this.dossierId = lotissementId;
    }

    public Long getNatureId() {
        return natureId;
    }

    public void setNatureId(Long natureId) {
        this.natureId = natureId;
    }

    public Long getActiviteId() {
        return activiteId;
    }

    public void setActiviteId(Long activiteId) {
        this.activiteId = activiteId;
    }

    public Long getUsageId() {
        return usageId;
    }

    public void setUsageId(Long usageId) {
        this.usageId = usageId;
    }

    public Long getProprietaireId() {
        return proprietaireId;
    }

    public void setProprietaireId(Long proprietaireId) {
        this.proprietaireId = proprietaireId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DossierDTO)) {
            return false;
        }

        return id != null && id.equals(((DossierDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DossierDTO{" +
            "id=" + getId() +
            ", numero='" + getNumero() + "'" +
            ", montantLoyer=" + getMontantLoyer() +
            ", superficieBatie=" + getSuperficieBatie() +
            ", coeffCorrectifBatie=" + getCoeffCorrectifBatie() +
            ", valeurBatie=" + getValeurBatie() +
            ", lineaireCloture=" + getLineaireCloture() +
            ", coeffCloture=" + getCoeffCloture() +
            ", valeurCloture=" + getValeurCloture() +
            ", amenagementSpaciaux='" + getAmenagementSpaciaux() + "'" +
            ", valeurAmenagement=" + getValeurAmenagement() +
            ", valeurVenale=" + getValeurVenale() +
            ", valeurLocativ='" + getValeurLocativ() + "'" +
            ", paysAdresse='" + getPaysAdresse() + "'" +
            ", villeAdresse='" + getVilleAdresse() + "'" +
            ", parcelles='" + getParcelles() + "'" +
            ", dossierId=" + getDossierId() +
            ", natureId=" + getNatureId() +
            ", activiteId=" + getActiviteId() +
            ", usageId=" + getUsageId() +
            ", proprietaireId=" + getProprietaireId() +
            "}";
    }
}
