import { IDossier } from 'app/shared/model/dossier.model';

export interface IProprietaire {
  id?: number;
  prenom?: string;
  nom?: string;
  raisonSocial?: string;
  personneMorale?: boolean;
  lieuNaissance?: string;
  numCNIi?: string;
  ninea?: string;
  adresse?: string;
  telephone?: string;
  telephone2?: string;
  telephone3?: string;
  aquisition?: string;
  dossiers?: IDossier[];
}

export class Proprietaire implements IProprietaire {
  constructor(
    public id?: number,
    public prenom?: string,
    public nom?: string,
    public raisonSocial?: string,
    public personneMorale?: boolean,
    public lieuNaissance?: string,
    public numCNIi?: string,
    public ninea?: string,
    public adresse?: string,
    public telephone?: string,
    public telephone2?: string,
    public telephone3?: string,
    public aquisition?: string,
    public dossiers?: IDossier[]
  ) {
    this.personneMorale = this.personneMorale || false;
  }
}
