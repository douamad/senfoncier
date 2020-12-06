package com.pirtol.lab.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Lotissement.
 */
@Entity
@Table(name = "lotissement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Lotissement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "libelle")
    private String libelle;

    @OneToMany(mappedBy = "dossier")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Dossier> lotissements = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "lotissements", allowSetters = true)
    private Quartier quartier;

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

    public Lotissement code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public Lotissement libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<Dossier> getLotissements() {
        return lotissements;
    }

    public Lotissement lotissements(Set<Dossier> dossiers) {
        this.lotissements = dossiers;
        return this;
    }

    public Lotissement addLotissement(Dossier dossier) {
        this.lotissements.add(dossier);
        dossier.setDossier(this);
        return this;
    }

    public Lotissement removeLotissement(Dossier dossier) {
        this.lotissements.remove(dossier);
        dossier.setDossier(null);
        return this;
    }

    public void setLotissements(Set<Dossier> dossiers) {
        this.lotissements = dossiers;
    }

    public Quartier getQuartier() {
        return quartier;
    }

    public Lotissement quartier(Quartier quartier) {
        this.quartier = quartier;
        return this;
    }

    public void setQuartier(Quartier quartier) {
        this.quartier = quartier;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lotissement)) {
            return false;
        }
        return id != null && id.equals(((Lotissement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Lotissement{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
