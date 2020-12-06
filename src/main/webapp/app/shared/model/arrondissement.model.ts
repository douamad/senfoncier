import { ICommune } from 'app/shared/model/commune.model';

export interface IArrondissement {
  id?: number;
  code?: string;
  libelle?: string;
  communes?: ICommune[];
  departementId?: number;
}

export class Arrondissement implements IArrondissement {
  constructor(
    public id?: number,
    public code?: string,
    public libelle?: string,
    public communes?: ICommune[],
    public departementId?: number
  ) {}
}
