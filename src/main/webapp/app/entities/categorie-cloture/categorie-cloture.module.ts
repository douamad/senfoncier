import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SenfoncierSharedModule } from 'app/shared/shared.module';
import { CategorieClotureComponent } from './categorie-cloture.component';
import { CategorieClotureDetailComponent } from './categorie-cloture-detail.component';
import { CategorieClotureUpdateComponent } from './categorie-cloture-update.component';
import { CategorieClotureDeleteDialogComponent } from './categorie-cloture-delete-dialog.component';
import { categorieClotureRoute } from './categorie-cloture.route';

@NgModule({
  imports: [SenfoncierSharedModule, RouterModule.forChild(categorieClotureRoute)],
  declarations: [
    CategorieClotureComponent,
    CategorieClotureDetailComponent,
    CategorieClotureUpdateComponent,
    CategorieClotureDeleteDialogComponent,
  ],
  entryComponents: [CategorieClotureDeleteDialogComponent],
})
export class SenfoncierCategorieClotureModule {}
