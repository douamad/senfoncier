import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IQuartier } from 'app/shared/model/quartier.model';
import { QuartierService } from './quartier.service';

@Component({
  templateUrl: './quartier-delete-dialog.component.html',
})
export class QuartierDeleteDialogComponent {
  quartier?: IQuartier;

  constructor(protected quartierService: QuartierService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.quartierService.delete(id).subscribe(() => {
      this.eventManager.broadcast('quartierListModification');
      this.activeModal.close();
    });
  }
}
