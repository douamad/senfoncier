import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SenfoncierSharedModule } from 'app/shared/shared.module';
import { CommuneComponent } from './commune.component';
import { CommuneDetailComponent } from './commune-detail.component';
import { CommuneUpdateComponent } from './commune-update.component';
import { CommuneDeleteDialogComponent } from './commune-delete-dialog.component';
import { communeRoute } from './commune.route';

@NgModule({
  imports: [SenfoncierSharedModule, RouterModule.forChild(communeRoute)],
  declarations: [CommuneComponent, CommuneDetailComponent, CommuneUpdateComponent, CommuneDeleteDialogComponent],
  entryComponents: [CommuneDeleteDialogComponent],
})
export class SenfoncierCommuneModule {}
