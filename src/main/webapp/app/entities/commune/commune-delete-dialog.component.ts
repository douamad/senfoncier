import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICommune } from 'app/shared/model/commune.model';
import { CommuneService } from './commune.service';

@Component({
  templateUrl: './commune-delete-dialog.component.html',
})
export class CommuneDeleteDialogComponent {
  commune?: ICommune;

  constructor(protected communeService: CommuneService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.communeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('communeListModification');
      this.activeModal.close();
    });
  }
}
