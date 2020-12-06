import { ILotissement } from 'app/shared/model/lotissement.model';

export interface IQuartier {
  id?: number;
  code?: string;
  libelle?: string;
  lotissements?: ILotissement[];
  commununeId?: number;
}

export class Quartier implements IQuartier {
  constructor(
    public id?: number,
    public code?: string,
    public libelle?: string,
    public lotissements?: ILotissement[],
    public commununeId?: number
  ) {}
}
