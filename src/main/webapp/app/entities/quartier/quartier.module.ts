import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SenfoncierSharedModule } from 'app/shared/shared.module';
import { QuartierComponent } from './quartier.component';
import { QuartierDetailComponent } from './quartier-detail.component';
import { QuartierUpdateComponent } from './quartier-update.component';
import { QuartierDeleteDialogComponent } from './quartier-delete-dialog.component';
import { quartierRoute } from './quartier.route';

@NgModule({
  imports: [SenfoncierSharedModule, RouterModule.forChild(quartierRoute)],
  declarations: [QuartierComponent, QuartierDetailComponent, QuartierUpdateComponent, QuartierDeleteDialogComponent],
  entryComponents: [QuartierDeleteDialogComponent],
})
export class SenfoncierQuartierModule {}
