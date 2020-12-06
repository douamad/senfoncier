import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SenfoncierSharedModule } from 'app/shared/shared.module';
import { LotissementComponent } from './lotissement.component';
import { LotissementDetailComponent } from './lotissement-detail.component';
import { LotissementUpdateComponent } from './lotissement-update.component';
import { LotissementDeleteDialogComponent } from './lotissement-delete-dialog.component';
import { lotissementRoute } from './lotissement.route';

@NgModule({
  imports: [SenfoncierSharedModule, RouterModule.forChild(lotissementRoute)],
  declarations: [LotissementComponent, LotissementDetailComponent, LotissementUpdateComponent, LotissementDeleteDialogComponent],
  entryComponents: [LotissementDeleteDialogComponent],
})
export class SenfoncierLotissementModule {}
