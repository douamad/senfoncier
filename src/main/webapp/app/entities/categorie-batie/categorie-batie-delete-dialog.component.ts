import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICategorieBatie } from 'app/shared/model/categorie-batie.model';
import { CategorieBatieService } from './categorie-batie.service';

@Component({
  templateUrl: './categorie-batie-delete-dialog.component.html',
})
export class CategorieBatieDeleteDialogComponent {
  categorieBatie?: ICategorieBatie;

  constructor(
    protected categorieBatieService: CategorieBatieService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.categorieBatieService.delete(id).subscribe(() => {
      this.eventManager.broadcast('categorieBatieListModification');
      this.activeModal.close();
    });
  }
}
