import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IArrondissement, Arrondissement } from 'app/shared/model/arrondissement.model';
import { ArrondissementService } from './arrondissement.service';
import { IDepartement } from 'app/shared/model/departement.model';
import { DepartementService } from 'app/entities/departement/departement.service';

@Component({
  selector: 'jhi-arrondissement-update',
  templateUrl: './arrondissement-update.component.html',
})
export class ArrondissementUpdateComponent implements OnInit {
  isSaving = false;
  departements: IDepartement[] = [];

  editForm = this.fb.group({
    id: [],
    code: [],
    libelle: [],
    departementId: [],
  });

  constructor(
    protected arrondissementService: ArrondissementService,
    protected departementService: DepartementService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ arrondissement }) => {
      this.updateForm(arrondissement);

      this.departementService.query().subscribe((res: HttpResponse<IDepartement[]>) => (this.departements = res.body || []));
    });
  }

  updateForm(arrondissement: IArrondissement): void {
    this.editForm.patchValue({
      id: arrondissement.id,
      code: arrondissement.code,
      libelle: arrondissement.libelle,
      departementId: arrondissement.departementId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const arrondissement = this.createFromForm();
    if (arrondissement.id !== undefined) {
      this.subscribeToSaveResponse(this.arrondissementService.update(arrondissement));
    } else {
      this.subscribeToSaveResponse(this.arrondissementService.create(arrondissement));
    }
  }

  private createFromForm(): IArrondissement {
    return {
      ...new Arrondissement(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      departementId: this.editForm.get(['departementId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IArrondissement>>): void {
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

  trackById(index: number, item: IDepartement): any {
    return item.id;
  }
}
