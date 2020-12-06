import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICommune, Commune } from 'app/shared/model/commune.model';
import { CommuneService } from './commune.service';
import { IArrondissement } from 'app/shared/model/arrondissement.model';
import { ArrondissementService } from 'app/entities/arrondissement/arrondissement.service';

@Component({
  selector: 'jhi-commune-update',
  templateUrl: './commune-update.component.html',
})
export class CommuneUpdateComponent implements OnInit {
  isSaving = false;
  arrondissements: IArrondissement[] = [];

  editForm = this.fb.group({
    id: [],
    code: [],
    libelle: [],
    arrondissementId: [],
  });

  constructor(
    protected communeService: CommuneService,
    protected arrondissementService: ArrondissementService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ commune }) => {
      this.updateForm(commune);

      this.arrondissementService.query().subscribe((res: HttpResponse<IArrondissement[]>) => (this.arrondissements = res.body || []));
    });
  }

  updateForm(commune: ICommune): void {
    this.editForm.patchValue({
      id: commune.id,
      code: commune.code,
      libelle: commune.libelle,
      arrondissementId: commune.arrondissementId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const commune = this.createFromForm();
    if (commune.id !== undefined) {
      this.subscribeToSaveResponse(this.communeService.update(commune));
    } else {
      this.subscribeToSaveResponse(this.communeService.create(commune));
    }
  }

  private createFromForm(): ICommune {
    return {
      ...new Commune(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      arrondissementId: this.editForm.get(['arrondissementId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICommune>>): void {
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

  trackById(index: number, item: IArrondissement): any {
    return item.id;
  }
}
