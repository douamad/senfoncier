package com.pirtol.lab.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Arrondissement.
 */
@Entity
@Table(name = "arrondissement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Arrondissement implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "libelle")
    private String libelle;

    @OneToMany(mappedBy = "arrondissement")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Commune> communes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "arrondissements", allowSetters = true)
    private Departement departement;

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

    public Arrondissement code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public Arrondissement libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<Commune> getCommunes() {
        return communes;
    }

    public Arrondissement communes(Set<Commune> communes) {
        this.communes = communes;
        return this;
    }

    public Arrondissement addCommune(Commune commune) {
        this.communes.add(commune);
        commune.setArrondissement(this);
        return this;
    }

    public Arrondissement removeCommune(Commune commune) {
        this.communes.remove(commune);
        commune.setArrondissement(null);
        return this;
    }

    public void setCommunes(Set<Commune> communes) {
        this.communes = communes;
    }

    public Departement getDepartement() {
        return departement;
    }

    public Arrondissement departement(Departement departement) {
        this.departement = departement;
        return this;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Arrondissement)) {
            return false;
        }
        return id != null && id.equals(((Arrondissement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Arrondissement{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
