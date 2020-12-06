import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICategorieCloture, CategorieCloture } from 'app/shared/model/categorie-cloture.model';
import { CategorieClotureService } from './categorie-cloture.service';
import { IDossier } from 'app/shared/model/dossier.model';
import { DossierService } from 'app/entities/dossier/dossier.service';

@Component({
  selector: 'jhi-categorie-cloture-update',
  templateUrl: './categorie-cloture-update.component.html',
})
export class CategorieClotureUpdateComponent implements OnInit {
  isSaving = false;
  dossiers: IDossier[] = [];

  editForm = this.fb.group({
    id: [],
    libelle: [],
    prixMetreCare: [],
    dossierId: [],
    dossierId: [],
  });

  constructor(
    protected categorieClotureService: CategorieClotureService,
    protected dossierService: DossierService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ categorieCloture }) => {
      this.updateForm(categorieCloture);

      this.dossierService.query().subscribe((res: HttpResponse<IDossier[]>) => (this.dossiers = res.body || []));
    });
  }

  updateForm(categorieCloture: ICategorieCloture): void {
    this.editForm.patchValue({
      id: categorieCloture.id,
      libelle: categorieCloture.libelle,
      prixMetreCare: categorieCloture.prixMetreCare,
      dossierId: categorieCloture.dossierId,
      dossierId: categorieCloture.dossierId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const categorieCloture = this.createFromForm();
    if (categorieCloture.id !== undefined) {
      this.subscribeToSaveResponse(this.categorieClotureService.update(categorieCloture));
    } else {
      this.subscribeToSaveResponse(this.categorieClotureService.create(categorieCloture));
    }
  }

  private createFromForm(): ICategorieCloture {
    return {
      ...new CategorieCloture(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      prixMetreCare: this.editForm.get(['prixMetreCare'])!.value,
      dossierId: this.editForm.get(['dossierId'])!.value,
      dossierId: this.editForm.get(['dossierId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICategorieCloture>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IDossier): any {
    return item.id;
  }
}
