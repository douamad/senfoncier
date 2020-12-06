package com.pirtol.lab.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Nature.
 */
@Entity
@Table(name = "nature")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Nature implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "libelle")
    private String libelle;

    @OneToMany(mappedBy = "nature")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Dossier> dossiers = new HashSet<>();

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

    public Nature code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public Nature libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<Dossier> getDossiers() {
        return dossiers;
    }

    public Nature dossiers(Set<Dossier> dossiers) {
        this.dossiers = dossiers;
        return this;
    }

    public Nature addDossier(Dossier dossier) {
        this.dossiers.add(dossier);
        dossier.setNature(this);
        return this;
    }

    public Nature removeDossier(Dossier dossier) {
        this.dossiers.remove(dossier);
        dossier.setNature(null);
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
        if (!(o instanceof Nature)) {
            return false;
        }
        return id != null && id.equals(((Nature) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Nature{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
