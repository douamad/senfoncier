import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILotissement } from 'app/shared/model/lotissement.model';
import { LotissementService } from './lotissement.service';

@Component({
  templateUrl: './lotissement-delete-dialog.component.html',
})
export class LotissementDeleteDialogComponent {
  lotissement?: ILotissement;

  constructor(
    protected lotissementService: LotissementService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.lotissementService.delete(id).subscribe(() => {
      this.eventManager.broadcast('lotissementListModification');
      this.activeModal.close();
    });
  }
}
