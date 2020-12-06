import { ICategorieBatie } from 'app/shared/model/categorie-batie.model';
import { ICategorieCloture } from 'app/shared/model/categorie-cloture.model';
import { IParcelle } from 'app/shared/model/parcelle.model';

export interface IDossier {
  id?: number;
  numero?: string;
  montantLoyer?: number;
  superficieBatie?: number;
  coeffCorrectifBatie?: number;
  valeurBatie?: number;
  lineaireCloture?: number;
  coeffCloture?: number;
  valeurCloture?: number;
  amenagementSpaciaux?: string;
  valeurAmenagement?: number;
  valeurVenale?: number;
  valeurLocativ?: string;
  paysAdresse?: string;
  villeAdresse?: string;
  categorieBaties?: ICategorieBatie[];
  categoriteClotures?: ICategorieCloture[];
  parcelles?: IParcelle[];
  dossierId?: number;
  natureId?: number;
  activiteId?: number;
  usageId?: number;
  proprietaireId?: number;
}

export class Dossier implements IDossier {
  constructor(
    public id?: number,
    public numero?: string,
    public montantLoyer?: number,
    public superficieBatie?: number,
    public coeffCorrectifBatie?: number,
    public valeurBatie?: number,
    public lineaireCloture?: number,
    public coeffCloture?: number,
    public valeurCloture?: number,
    public amenagementSpaciaux?: string,
    public valeurAmenagement?: number,
    public valeurVenale?: number,
    public valeurLocativ?: string,
    public paysAdresse?: string,
    public villeAdresse?: string,
    public categorieBaties?: ICategorieBatie[],
    public categoriteClotures?: ICategorieCloture[],
    public parcelles?: IParcelle[],
    public dossierId?: number,
    public natureId?: number,
    public activiteId?: number,
    public usageId?: number,
    public proprietaireId?: number
  ) {}
}
