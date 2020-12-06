import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INature } from 'app/shared/model/nature.model';
import { NatureService } from './nature.service';

@Component({
  templateUrl: './nature-delete-dialog.component.html',
})
export class NatureDeleteDialogComponent {
  nature?: INature;

  constructor(protected natureService: NatureService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.natureService.delete(id).subscribe(() => {
      this.eventManager.broadcast('natureListModification');
      this.activeModal.close();
    });
  }
}
