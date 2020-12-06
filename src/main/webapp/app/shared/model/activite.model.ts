import { IDossier } from 'app/shared/model/dossier.model';

export interface IActivite {
  id?: number;
  code?: string;
  libelle?: string;
  dossiers?: IDossier[];
}

export class Activite implements IActivite {
  constructor(public id?: number, public code?: string, public libelle?: string, public dossiers?: IDossier[]) {}
}
