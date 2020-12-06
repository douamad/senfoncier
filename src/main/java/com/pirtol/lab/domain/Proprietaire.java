package com.pirtol.lab.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Proprietaire.
 */
@Entity
@Table(name = "proprietaire")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Proprietaire implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "nom")
    private String nom;

    @Column(name = "raison_social")
    private String raisonSocial;

    @Column(name = "personne_morale")
    private Boolean personneMorale;

    @Column(name = "lieu_naissance")
    private String lieuNaissance;

    @Column(name = "num_cn_ii")
    private String numCNIi;

    @Column(name = "ninea")
    private String ninea;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "telephone_2")
    private String telephone2;

    @Column(name = "telephone_3")
    private String telephone3;

    @Column(name = "aquisition")
    private String aquisition;

    @OneToMany(mappedBy = "proprietaire")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Dossier> dossiers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public Proprietaire prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public Proprietaire nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRaisonSocial() {
        return raisonSocial;
    }

    public Proprietaire raisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
        return this;
    }

    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }

    public Boolean isPersonneMorale() {
        return personneMorale;
    }

    public Proprietaire personneMorale(Boolean personneMorale) {
        this.personneMorale = personneMorale;
        return this;
    }

    public void setPersonneMorale(Boolean personneMorale) {
        this.personneMorale = personneMorale;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public Proprietaire lieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
        return this;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getNumCNIi() {
        return numCNIi;
    }

    public Proprietaire numCNIi(String numCNIi) {
        this.numCNIi = numCNIi;
        return this;
    }

    public void setNumCNIi(String numCNIi) {
        this.numCNIi = numCNIi;
    }

    public String getNinea() {
        return ninea;
    }

    public Proprietaire ninea(String ninea) {
        this.ninea = ninea;
        return this;
    }

    public void setNinea(String ninea) {
        this.ninea = ninea;
    }

    public String getAdresse() {
        return adresse;
    }

    public Proprietaire adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public Proprietaire telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public Proprietaire telephone2(String telephone2) {
        this.telephone2 = telephone2;
        return this;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public String getTelephone3() {
        return telephone3;
    }

    public Proprietaire telephone3(String telephone3) {
        this.telephone3 = telephone3;
        return this;
    }

    public void setTelephone3(String telephone3) {
        this.telephone3 = telephone3;
    }

    public String getAquisition() {
        return aquisition;
    }

    public Proprietaire aquisition(String aquisition) {
        this.aquisition = aquisition;
        return this;
    }

    public void setAquisition(String aquisition) {
        this.aquisition = aquisition;
    }

    public Set<Dossier> getDossiers() {
        return dossiers;
    }

    public Proprietaire dossiers(Set<Dossier> dossiers) {
        this.dossiers = dossiers;
        return this;
    }

    public Proprietaire addDossier(Dossier dossier) {
        this.dossiers.add(dossier);
        dossier.setProprietaire(this);
        return this;
    }

    public Proprietaire removeDossier(Dossier dossier) {
        this.dossiers.remove(dossier);
        dossier.setProprietaire(null);
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
        if (!(o instanceof Proprietaire)) {
            return false;
        }
        return id != null && id.equals(((Proprietaire) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Proprietaire{" +
            "id=" + getId() +
            ", prenom='" + getPrenom() + "'" +
            ", nom='" + getNom() + "'" +
            ", raisonSocial='" + getRaisonSocial() + "'" +
            ", personneMorale='" + isPersonneMorale() + "'" +
            ", lieuNaissance='" + getLieuNaissance() + "'" +
            ", numCNIi='" + getNumCNIi() + "'" +
            ", ninea='" + getNinea() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", telephone2='" + getTelephone2() + "'" +
            ", telephone3='" + getTelephone3() + "'" +
            ", aquisition='" + getAquisition() + "'" +
            "}";
    }
}
