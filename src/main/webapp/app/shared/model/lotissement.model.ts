import { IDossier } from 'app/shared/model/dossier.model';

export interface ILotissement {
  id?: number;
  code?: string;
  libelle?: string;
  lotissements?: IDossier[];
  quartierId?: number;
}

export class Lotissement implements ILotissement {
  constructor(
    public id?: number,
    public code?: string,
    public libelle?: string,
    public lotissements?: IDossier[],
    public quartierId?: number
  ) {}
}
