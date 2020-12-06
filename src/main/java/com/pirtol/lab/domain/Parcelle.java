package com.pirtol.lab.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Parcelle.
 */
@Entity
@Table(name = "parcelle")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Parcelle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "num_section")
    private String numSection;

    @Column(name = "numero_parcelle")
    private String numeroParcelle;

    @Column(name = "nicad")
    private String nicad;

    @Column(name = "superfici")
    private Double superfici;

    @Column(name = "titre_mere")
    private String titreMere;

    @Column(name = "titre_cree")
    private String titreCree;

    @ManyToMany(mappedBy = "parcelles")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<Dossier> dossiers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumSection() {
        return numSection;
    }

    public Parcelle numSection(String numSection) {
        this.numSection = numSection;
        return this;
    }

    public void setNumSection(String numSection) {
        this.numSection = numSection;
    }

    public String getNumeroParcelle() {
        return numeroParcelle;
    }

    public Parcelle numeroParcelle(String numeroParcelle) {
        this.numeroParcelle = numeroParcelle;
        return this;
    }

    public void setNumeroParcelle(String numeroParcelle) {
        this.numeroParcelle = numeroParcelle;
    }

    public String getNicad() {
        return nicad;
    }

    public Parcelle nicad(String nicad) {
        this.nicad = nicad;
        return this;
    }

    public void setNicad(String nicad) {
        this.nicad = nicad;
    }

    public Double getSuperfici() {
        return superfici;
    }

    public Parcelle superfici(Double superfici) {
        this.superfici = superfici;
        return this;
    }

    public void setSuperfici(Double superfici) {
        this.superfici = superfici;
    }

    public String getTitreMere() {
        return titreMere;
    }

    public Parcelle titreMere(String titreMere) {
        this.titreMere = titreMere;
        return this;
    }

    public void setTitreMere(String titreMere) {
        this.titreMere = titreMere;
    }

    public String getTitreCree() {
        return titreCree;
    }

    public Parcelle titreCree(String titreCree) {
        this.titreCree = titreCree;
        return this;
    }

    public void setTitreCree(String titreCree) {
        this.titreCree = titreCree;
    }

    public Set<Dossier> getDossiers() {
        return dossiers;
    }

    public Parcelle dossiers(Set<Dossier> dossiers) {
        this.dossiers = dossiers;
        return this;
    }

    public Parcelle addDossier(Dossier dossier) {
        this.dossiers.add(dossier);
        dossier.getParcelles().add(this);
        return this;
    }

    public Parcelle removeDossier(Dossier dossier) {
        this.dossiers.remove(dossier);
        dossier.getParcelles().remove(this);
        return this;
    }

    public void setDossiers(Set<Dossier> dossiers) {
        this.dossiers = dossiers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Parcelle)) {
            return false;
        }
        return id != null && id.equals(((Parcelle) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Parcelle{" +
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
