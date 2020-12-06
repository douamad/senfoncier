import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProprietaire } from 'app/shared/model/proprietaire.model';
import { ProprietaireService } from './proprietaire.service';

@Component({
  templateUrl: './proprietaire-delete-dialog.component.html',
})
export class ProprietaireDeleteDialogComponent {
  proprietaire?: IProprietaire;

  constructor(
    protected proprietaireService: ProprietaireService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.proprietaireService.delete(id).subscribe(() => {
      this.eventManager.broadcast('proprietaireListModification');
      this.activeModal.close();
    });
  }
}
