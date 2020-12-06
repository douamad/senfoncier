import { IDossier } from 'app/shared/model/dossier.model';

export interface IUsage {
  id?: number;
  code?: string;
  libelle?: string;
  dossiers?: IDossier[];
}

export class Usage implements IUsage {
  constructor(public id?: number, public code?: string, public libelle?: string, public dossiers?: IDossier[]) {}
}
