import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IArrondissement } from 'app/shared/model/arrondissement.model';
import { ArrondissementService } from './arrondissement.service';

@Component({
  templateUrl: './arrondissement-delete-dialog.component.html',
})
export class ArrondissementDeleteDialogComponent {
  arrondissement?: IArrondissement;

  constructor(
    protected arrondissementService: ArrondissementService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.arrondissementService.delete(id).subscribe(() => {
      this.eventManager.broadcast('arrondissementListModification');
      this.activeModal.close();
    });
  }
}
