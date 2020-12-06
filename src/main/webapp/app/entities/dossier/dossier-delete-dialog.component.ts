import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDossier } from 'app/shared/model/dossier.model';
import { DossierService } from './dossier.service';

@Component({
  templateUrl: './dossier-delete-dialog.component.html',
})
export class DossierDeleteDialogComponent {
  dossier?: IDossier;

  constructor(protected dossierService: DossierService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.dossierService.delete(id).subscribe(() => {
      this.eventManager.broadcast('dossierListModification');
      this.activeModal.close();
    });
  }
}
