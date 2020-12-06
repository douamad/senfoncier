import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IParcelle } from 'app/shared/model/parcelle.model';
import { ParcelleService } from './parcelle.service';

@Component({
  templateUrl: './parcelle-delete-dialog.component.html',
})
export class ParcelleDeleteDialogComponent {
  parcelle?: IParcelle;

  constructor(protected parcelleService: ParcelleService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.parcelleService.delete(id).subscribe(() => {
      this.eventManager.broadcast('parcelleListModification');
      this.activeModal.close();
    });
  }
}
