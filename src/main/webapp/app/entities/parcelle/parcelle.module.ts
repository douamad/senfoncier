import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SenfoncierSharedModule } from 'app/shared/shared.module';
import { ParcelleComponent } from './parcelle.component';
import { ParcelleDetailComponent } from './parcelle-detail.component';
import { ParcelleUpdateComponent } from './parcelle-update.component';
import { ParcelleDeleteDialogComponent } from './parcelle-delete-dialog.component';
import { parcelleRoute } from './parcelle.route';

@NgModule({
  imports: [SenfoncierSharedModule, RouterModule.forChild(parcelleRoute)],
  declarations: [ParcelleComponent, ParcelleDetailComponent, ParcelleUpdateComponent, ParcelleDeleteDialogComponent],
  entryComponents: [ParcelleDeleteDialogComponent],
})
export class SenfoncierParcelleModule {}
