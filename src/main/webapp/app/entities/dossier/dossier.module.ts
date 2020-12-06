import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SenfoncierSharedModule } from 'app/shared/shared.module';
import { DossierComponent } from './dossier.component';
import { DossierDetailComponent } from './dossier-detail.component';
import { DossierUpdateComponent } from './dossier-update.component';
import { DossierDeleteDialogComponent } from './dossier-delete-dialog.component';
import { dossierRoute } from './dossier.route';

@NgModule({
  imports: [SenfoncierSharedModule, RouterModule.forChild(dossierRoute)],
  declarations: [DossierComponent, DossierDetailComponent, DossierUpdateComponent, DossierDeleteDialogComponent],
  entryComponents: [DossierDeleteDialogComponent],
})
export class SenfoncierDossierModule {}
