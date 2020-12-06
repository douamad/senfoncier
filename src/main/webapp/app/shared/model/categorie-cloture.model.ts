export interface ICategorieCloture {
  id?: number;
  libelle?: string;
  prixMetreCare?: number;
  dossierId?: number;
}

export class CategorieCloture implements ICategorieCloture {
  constructor(public id?: number, public libelle?: string, public prixMetreCare?: number, public dossierId?: number) {}
}
