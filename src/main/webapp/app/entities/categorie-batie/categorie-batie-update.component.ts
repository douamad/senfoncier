import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICategorieBatie, CategorieBatie } from 'app/shared/model/categorie-batie.model';
import { CategorieBatieService } from './categorie-batie.service';
import { IDossier } from 'app/shared/model/dossier.model';
import { DossierService } from 'app/entities/dossier/dossier.service';

@Component({
  selector: 'jhi-categorie-batie-update',
  templateUrl: './categorie-batie-update.component.html',
})
export class CategorieBatieUpdateComponent implements OnInit {
  isSaving = false;
  dossiers: IDossier[] = [];

  editForm = this.fb.group({
    id: [],
    libelle: [],
    prixMetreCare: [],
    dossierId: [],
  });

  constructor(
    protected categorieBatieService: CategorieBatieService,
    protected dossierService: DossierService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ categorieBatie }) => {
      this.updateForm(categorieBatie);

      this.dossierService.query().subscribe((res: HttpResponse<IDossier[]>) => (this.dossiers = res.body || []));
    });
  }

  updateForm(categorieBatie: ICategorieBatie): void {
    this.editForm.patchValue({
      id: categorieBatie.id,
      libelle: categorieBatie.libelle,
      prixMetreCare: categorieBatie.prixMetreCare,
      dossierId: categorieBatie.dossierId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const categorieBatie = this.createFromForm();
    if (categorieBatie.id !== undefined) {
      this.subscribeToSaveResponse(this.categorieBatieService.update(categorieBatie));
    } else {
      this.subscribeToSaveResponse(this.categorieBatieService.create(categorieBatie));
    }
  }

  private createFromForm(): ICategorieBatie {
    return {
      ...new CategorieBatie(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      prixMetreCare: this.editForm.get(['prixMetreCare'])!.value,
      dossierId: this.editForm.get(['dossierId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICategorieBatie>>): void {
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
