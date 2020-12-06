import { IDossier } from 'app/shared/model/dossier.model';

export interface INature {
  id?: number;
  code?: string;
  libelle?: string;
  dossiers?: IDossier[];
}

export class Nature implements INature {
  constructor(public id?: number, public code?: string, public libelle?: string, public dossiers?: IDossier[]) {}
}
