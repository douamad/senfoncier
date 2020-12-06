import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SenfoncierSharedModule } from 'app/shared/shared.module';
import { CategorieBatieComponent } from './categorie-batie.component';
import { CategorieBatieDetailComponent } from './categorie-batie-detail.component';
import { CategorieBatieUpdateComponent } from './categorie-batie-update.component';
import { CategorieBatieDeleteDialogComponent } from './categorie-batie-delete-dialog.component';
import { categorieBatieRoute } from './categorie-batie.route';

@NgModule({
  imports: [SenfoncierSharedModule, RouterModule.forChild(categorieBatieRoute)],
  declarations: [
    CategorieBatieComponent,
    CategorieBatieDetailComponent,
    CategorieBatieUpdateComponent,
    CategorieBatieDeleteDialogComponent,
  ],
  entryComponents: [CategorieBatieDeleteDialogComponent],
})
export class SenfoncierCategorieBatieModule {}
