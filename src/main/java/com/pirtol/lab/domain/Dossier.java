package com.pirtol.lab.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Dossier.
 */
@Entity
@Table(name = "dossier")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Dossier implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "numero")
    private String numero;

    @Column(name = "montant_loyer")
    private Double montantLoyer;

    @Column(name = "superficie_batie")
    private Double superficieBatie;

    @Column(name = "coeff_correctif_batie")
    private Double coeffCorrectifBatie;

    @Column(name = "valeur_batie")
    private Double valeurBatie;

    @Column(name = "lineaire_cloture")
    private Double lineaireCloture;

    @Column(name = "coeff_cloture")
    private Double coeffCloture;

    @Column(name = "valeur_cloture")
    private Double valeurCloture;

    @Column(name = "amenagement_spaciaux")
    private String amenagementSpaciaux;

    @Column(name = "valeur_amenagement")
    private Double valeurAmenagement;

    @Column(name = "valeur_venale")
    private Double valeurVenale;

    @Column(name = "valeur_locativ")
    private String valeurLocativ;

    @Column(name = "pays_adresse")
    private String paysAdresse;

    @Column(name = "ville_adresse")
    private String villeAdresse;

    @OneToMany(mappedBy = "dossier")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<CategorieBatie> categorieBaties = new HashSet<>();

    @OneToMany(mappedBy = "dossier")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<CategorieCloture> categoriteClotures = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(
        name = "dossier_parcelle",
        joinColumns = @JoinColumn(name = "dossier_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "parcelle_id", referencedColumnName = "id")
    )
    private Set<Parcelle> parcelles = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "lotissements", allowSetters = true)
    private Lotissement dossier;

    @ManyToOne
    @JsonIgnoreProperties(value = "dossiers", allowSetters = true)
    private Nature nature;

    @ManyToOne
    @JsonIgnoreProperties(value = "dossiers", allowSetters = true)
    private Activite activite;

    @ManyToOne
    @JsonIgnoreProperties(value = "dossiers", allowSetters = true)
    private Usage usage;

    @ManyToOne
    @JsonIgnoreProperties(value = "dossiers", allowSetters = true)
    private Proprietaire proprietaire;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public Dossier numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Double getMontantLoyer() {
        return montantLoyer;
    }

    public Dossier montantLoyer(Double montantLoyer) {
        this.montantLoyer = montantLoyer;
        return this;
    }

    public void setMontantLoyer(Double montantLoyer) {
        this.montantLoyer = montantLoyer;
    }

    public Double getSuperficieBatie() {
        return superficieBatie;
    }

    public Dossier superficieBatie(Double superficieBatie) {
        this.superficieBatie = superficieBatie;
        return this;
    }

    public void setSuperficieBatie(Double superficieBatie) {
        this.superficieBatie = superficieBatie;
    }

    public Double getCoeffCorrectifBatie() {
        return coeffCorrectifBatie;
    }

    public Dossier coeffCorrectifBatie(Double coeffCorrectifBatie) {
        this.coeffCorrectifBatie = coeffCorrectifBatie;
        return this;
    }

    public void setCoeffCorrectifBatie(Double coeffCorrectifBatie) {
        this.coeffCorrectifBatie = coeffCorrectifBatie;
    }

    public Double getValeurBatie() {
        return valeurBatie;
    }

    public Dossier valeurBatie(Double valeurBatie) {
        this.valeurBatie = valeurBatie;
        return this;
    }

    public void setValeurBatie(Double valeurBatie) {
        this.valeurBatie = valeurBatie;
    }

    public Double getLineaireCloture() {
        return lineaireCloture;
    }

    public Dossier lineaireCloture(Double lineaireCloture) {
        this.lineaireCloture = lineaireCloture;
        return this;
    }

    public void setLineaireCloture(Double lineaireCloture) {
        this.lineaireCloture = lineaireCloture;
    }

    public Double getCoeffCloture() {
        return coeffCloture;
    }

    public Dossier coeffCloture(Double coeffCloture) {
        this.coeffCloture = coeffCloture;
        return this;
    }

    public void setCoeffCloture(Double coeffCloture) {
        this.coeffCloture = coeffCloture;
    }

    public Double getValeurCloture() {
        return valeurCloture;
    }

    public Dossier valeurCloture(Double valeurCloture) {
        this.valeurCloture = valeurCloture;
        return this;
    }

    public void setValeurCloture(Double valeurCloture) {
        this.valeurCloture = valeurCloture;
    }

    public String getAmenagementSpaciaux() {
        return amenagementSpaciaux;
    }

    public Dossier amenagementSpaciaux(String amenagementSpaciaux) {
        this.amenagementSpaciaux = amenagementSpaciaux;
        return this;
    }

    public void setAmenagementSpaciaux(String amenagementSpaciaux) {
        this.amenagementSpaciaux = amenagementSpaciaux;
    }

    public Double getValeurAmenagement() {
        return valeurAmenagement;
    }

    public Dossier valeurAmenagement(Double valeurAmenagement) {
        this.valeurAmenagement = valeurAmenagement;
        return this;
    }

    public void setValeurAmenagement(Double valeurAmenagement) {
        this.valeurAmenagement = valeurAmenagement;
    }

    public Double getValeurVenale() {
        return valeurVenale;
    }

    public Dossier valeurVenale(Double valeurVenale) {
        this.valeurVenale = valeurVenale;
        return this;
    }

    public void setValeurVenale(Double valeurVenale) {
        this.valeurVenale = valeurVenale;
    }

    public String getValeurLocativ() {
        return valeurLocativ;
    }

    public Dossier valeurLocativ(String valeurLocativ) {
        this.valeurLocativ = valeurLocativ;
        return this;
    }

    public void setValeurLocativ(String valeurLocativ) {
        this.valeurLocativ = valeurLocativ;
    }

    public String getPaysAdresse() {
        return paysAdresse;
    }

    public Dossier paysAdresse(String paysAdresse) {
        this.paysAdresse = paysAdresse;
        return this;
    }

    public void setPaysAdresse(String paysAdresse) {
        this.paysAdresse = paysAdresse;
    }

    public String getVilleAdresse() {
        return villeAdresse;
    }

    public Dossier villeAdresse(String villeAdresse) {
        this.villeAdresse = villeAdresse;
        return this;
    }

    public void setVilleAdresse(String villeAdresse) {
        this.villeAdresse = villeAdresse;
    }

    public Set<CategorieBatie> getCategorieBaties() {
        return categorieBaties;
    }

    public Dossier categorieBaties(Set<CategorieBatie> categorieBaties) {
        this.categorieBaties = categorieBaties;
        return this;
    }

    public Dossier addCategorieBatie(CategorieBatie categorieBatie) {
        this.categorieBaties.add(categorieBatie);
        categorieBatie.setDossier(this);
        return this;
    }

    public Dossier removeCategorieBatie(CategorieBatie categorieBatie) {
        this.categorieBaties.remove(categorieBatie);
        categorieBatie.setDossier(null);
        return this;
    }

    public void setCategorieBaties(Set<CategorieBatie> categorieBaties) {
        this.categorieBaties = categorieBaties;
    }

    public Set<CategorieCloture> getCategoriteClotures() {
        return categoriteClotures;
    }

    public Dossier categoriteClotures(Set<CategorieCloture> categorieClotures) {
        this.categoriteClotures = categorieClotures;
        return this;
    }

    public Dossier addCategoriteCloture(CategorieCloture categorieCloture) {
        this.categoriteClotures.add(categorieCloture);
        categorieCloture.setDossier(this);
        return this;
    }

    public Dossier removeCategoriteCloture(CategorieCloture categorieCloture) {
        this.categoriteClotures.remove(categorieCloture);
        categorieCloture.setDossier(null);
        return this;
    }

    public void setCategoriteClotures(Set<CategorieCloture> categorieClotures) {
        this.categoriteClotures = categorieClotures;
    }

    public Set<Parcelle> getParcelles() {
        return parcelles;
    }

    public Dossier parcelles(Set<Parcelle> parcelles) {
        this.parcelles = parcelles;
        return this;
    }

    public Dossier addParcelle(Parcelle parcelle) {
        this.parcelles.add(parcelle);
        parcelle.getDossiers().add(this);
        return this;
    }

    public Dossier removeParcelle(Parcelle parcelle) {
        this.parcelles.remove(parcelle);
        parcelle.getDossiers().remove(this);
        return this;
    }

    public void setParcelles(Set<Parcelle> parcelles) {
        this.parcelles = parcelles;
    }

    public Lotissement getDossier() {
        return dossier;
    }

    public Dossier dossier(Lotissement lotissement) {
        this.dossier = lotissement;
        return this;
    }

    public void setDossier(Lotissement lotissement) {
        this.dossier = lotissement;
    }

    public Nature getNature() {
        return nature;
    }

    public Dossier nature(Nature nature) {
        this.nature = nature;
        return this;
    }

    public void setNature(Nature nature) {
        this.nature = nature;
    }

    public Activite getActivite() {
        return activite;
    }

    public Dossier activite(Activite activite) {
        this.activite = activite;
        return this;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    public Usage getUsage() {
        return usage;
    }

    public Dossier usage(Usage usage) {
        this.usage = usage;
        return this;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public Proprietaire getProprietaire() {
        return proprietaire;
    }

    public Dossier proprietaire(Proprietaire proprietaire) {
        this.proprietaire = proprietaire;
        return this;
    }

    public void setProprietaire(Proprietaire proprietaire) {
        this.proprietaire = proprietaire;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dossier)) {
            return false;
        }
        return id != null && id.equals(((Dossier) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Dossier{" +
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
            "}";
    }
}
