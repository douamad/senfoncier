import { IQuartier } from 'app/shared/model/quartier.model';

export interface ICommune {
  id?: number;
  code?: string;
  libelle?: string;
  quartiers?: IQuartier[];
  arrondissementId?: number;
}

export class Commune implements ICommune {
  constructor(
    public id?: number,
    public code?: string,
    public libelle?: string,
    public quartiers?: IQuartier[],
    public arrondissementId?: number
  ) {}
}
