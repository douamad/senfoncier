import { IDepartement } from 'app/shared/model/departement.model';

export interface IRegion {
  id?: number;
  code?: string;
  libelle?: string;
  departements?: IDepartement[];
}

export class Region implements IRegion {
  constructor(public id?: number, public code?: string, public libelle?: string, public departements?: IDepartement[]) {}
}
