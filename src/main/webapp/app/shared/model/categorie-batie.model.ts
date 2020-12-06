export interface ICategorieBatie {
  id?: number;
  libelle?: string;
  prixMetreCare?: number;
  dossierId?: number;
}

export class CategorieBatie implements ICategorieBatie {
  constructor(public id?: number, public libelle?: string, public prixMetreCare?: number, public dossierId?: number) {}
}
