import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SenfoncierSharedModule } from 'app/shared/shared.module';
import { NatureComponent } from './nature.component';
import { NatureDetailComponent } from './nature-detail.component';
import { NatureUpdateComponent } from './nature-update.component';
import { NatureDeleteDialogComponent } from './nature-delete-dialog.component';
import { natureRoute } from './nature.route';

@NgModule({
  imports: [SenfoncierSharedModule, RouterModule.forChild(natureRoute)],
  declarations: [NatureComponent, NatureDetailComponent, NatureUpdateComponent, NatureDeleteDialogComponent],
  entryComponents: [NatureDeleteDialogComponent],
})
export class SenfoncierNatureModule {}
