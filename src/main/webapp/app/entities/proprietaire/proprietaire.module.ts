import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SenfoncierSharedModule } from 'app/shared/shared.module';
import { ProprietaireComponent } from './proprietaire.component';
import { ProprietaireDetailComponent } from './proprietaire-detail.component';
import { ProprietaireUpdateComponent } from './proprietaire-update.component';
import { ProprietaireDeleteDialogComponent } from './proprietaire-delete-dialog.component';
import { proprietaireRoute } from './proprietaire.route';

@NgModule({
  imports: [SenfoncierSharedModule, RouterModule.forChild(proprietaireRoute)],
  declarations: [ProprietaireComponent, ProprietaireDetailComponent, ProprietaireUpdateComponent, ProprietaireDeleteDialogComponent],
  entryComponents: [ProprietaireDeleteDialogComponent],
})
export class SenfoncierProprietaireModule {}
