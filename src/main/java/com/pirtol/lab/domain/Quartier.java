package com.pirtol.lab.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Quartier.
 */
@Entity
@Table(name = "quartier")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Quartier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "libelle")
    private String libelle;

    @OneToMany(mappedBy = "quartier")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Lotissement> lotissements = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "quartiers", allowSetters = true)
    private Commune communune;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public Quartier code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public Quartier libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<Lotissement> getLotissements() {
        return lotissements;
    }

    public Quartier lotissements(Set<Lotissement> lotissements) {
        this.lotissements = lotissements;
        return this;
    }

    public Quartier addLotissement(Lotissement lotissement) {
        this.lotissements.add(lotissement);
        lotissement.setQuartier(this);
        return this;
    }

    public Quartier removeLotissement(Lotissement lotissement) {
        this.lotissements.remove(lotissement);
        lotissement.setQuartier(null);
        return this;
    }

    public void setLotissements(Set<Lotissement> lotissements) {
        this.lotissements = lotissements;
    }

    public Commune getCommunune() {
        return communune;
    }

    public Quartier communune(Commune commune) {
        this.communune = commune;
        return this;
    }

    public void setCommunune(Commune commune) {
        this.communune = commune;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Quartier)) {
            return false;
        }
        return id != null && id.equals(((Quartier) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Quartier{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
