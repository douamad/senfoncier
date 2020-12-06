import { IArrondissement } from 'app/shared/model/arrondissement.model';

export interface IDepartement {
  id?: number;
  code?: string;
  libelle?: string;
  arrondissements?: IArrondissement[];
  regionId?: number;
}

export class Departement implements IDepartement {
  constructor(
    public id?: number,
    public code?: string,
    public libelle?: string,
    public arrondissements?: IArrondissement[],
    public regionId?: number
  ) {}
}
