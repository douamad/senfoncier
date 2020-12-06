import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'dossier',
        loadChildren: () => import('./dossier/dossier.module').then(m => m.SenfoncierDossierModule),
      },
      {
        path: 'region',
        loadChildren: () => import('./region/region.module').then(m => m.SenfoncierRegionModule),
      },
      {
        path: 'departement',
        loadChildren: () => import('./departement/departement.module').then(m => m.SenfoncierDepartementModule),
      },
      {
        path: 'arrondissement',
        loadChildren: () => import('./arrondissement/arrondissement.module').then(m => m.SenfoncierArrondissementModule),
      },
      {
        path: 'commune',
        loadChildren: () => import('./commune/commune.module').then(m => m.SenfoncierCommuneModule),
      },
      {
        path: 'quartier',
        loadChildren: () => import('./quartier/quartier.module').then(m => m.SenfoncierQuartierModule),
      },
      {
        path: 'lotissement',
        loadChildren: () => import('./lotissement/lotissement.module').then(m => m.SenfoncierLotissementModule),
      },
      {
        path: 'nature',
        loadChildren: () => import('./nature/nature.module').then(m => m.SenfoncierNatureModule),
      },
      {
        path: 'usage',
        loadChildren: () => import('./usage/usage.module').then(m => m.SenfoncierUsageModule),
      },
      {
        path: 'activite',
        loadChildren: () => import('./activite/activite.module').then(m => m.SenfoncierActiviteModule),
      },
      {
        path: 'parcelle',
        loadChildren: () => import('./parcelle/parcelle.module').then(m => m.SenfoncierParcelleModule),
      },
      {
        path: 'proprietaire',
        loadChildren: () => import('./proprietaire/proprietaire.module').then(m => m.SenfoncierProprietaireModule),
      },
      {
        path: 'categorie-batie',
        loadChildren: () => import('./categorie-batie/categorie-batie.module').then(m => m.SenfoncierCategorieBatieModule),
      },
      {
        path: 'categorie-cloture',
        loadChildren: () => import('./categorie-cloture/categorie-cloture.module').then(m => m.SenfoncierCategorieClotureModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class SenfoncierEntityModule {}
