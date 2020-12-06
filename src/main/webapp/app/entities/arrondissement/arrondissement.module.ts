import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SenfoncierSharedModule } from 'app/shared/shared.module';
import { ArrondissementComponent } from './arrondissement.component';
import { ArrondissementDetailComponent } from './arrondissement-detail.component';
import { ArrondissementUpdateComponent } from './arrondissement-update.component';
import { ArrondissementDeleteDialogComponent } from './arrondissement-delete-dialog.component';
import { arrondissementRoute } from './arrondissement.route';

@NgModule({
  imports: [SenfoncierSharedModule, RouterModule.forChild(arrondissementRoute)],
  declarations: [
    ArrondissementComponent,
    ArrondissementDetailComponent,
    ArrondissementUpdateComponent,
    ArrondissementDeleteDialogComponent,
  ],
  entryComponents: [ArrondissementDeleteDialogComponent],
})
export class SenfoncierArrondissementModule {}
