import { IDossier } from 'app/shared/model/dossier.model';

export interface IParcelle {
  id?: number;
  numSection?: string;
  numeroParcelle?: string;
  nicad?: string;
  superfici?: number;
  titreMere?: string;
  titreCree?: string;
  dossiers?: IDossier[];
}

export class Parcelle implements IParcelle {
  constructor(
    public id?: number,
    public numSection?: string,
    public numeroParcelle?: string,
    public nicad?: string,
    public superfici?: number,
    public titreMere?: string,
    public titreCree?: string,
    public dossiers?: IDossier[]
  ) {}
}
