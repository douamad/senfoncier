import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICategorieCloture } from 'app/shared/model/categorie-cloture.model';
import { CategorieClotureService } from './categorie-cloture.service';

@Component({
  templateUrl: './categorie-cloture-delete-dialog.component.html',
})
export class CategorieClotureDeleteDialogComponent {
  categorieCloture?: ICategorieCloture;

  constructor(
    protected categorieClotureService: CategorieClotureService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.categorieClotureService.delete(id).subscribe(() => {
      this.eventManager.broadcast('categorieClotureListModification');
      this.activeModal.close();
    });
  }
}
