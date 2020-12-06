import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IQuartier, Quartier } from 'app/shared/model/quartier.model';
import { QuartierService } from './quartier.service';
import { ICommune } from 'app/shared/model/commune.model';
import { CommuneService } from 'app/entities/commune/commune.service';

@Component({
  selector: 'jhi-quartier-update',
  templateUrl: './quartier-update.component.html',
})
export class QuartierUpdateComponent implements OnInit {
  isSaving = false;
  communes: ICommune[] = [];

  editForm = this.fb.group({
    id: [],
    code: [],
    libelle: [],
    commununeId: [],
  });

  constructor(
    protected quartierService: QuartierService,
    protected communeService: CommuneService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ quartier }) => {
      this.updateForm(quartier);

      this.communeService.query().subscribe((res: HttpResponse<ICommune[]>) => (this.communes = res.body || []));
    });
  }

  updateForm(quartier: IQuartier): void {
    this.editForm.patchValue({
      id: quartier.id,
      code: quartier.code,
      libelle: quartier.libelle,
      commununeId: quartier.commununeId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const quartier = this.createFromForm();
    if (quartier.id !== undefined) {
      this.subscribeToSaveResponse(this.quartierService.update(quartier));
    } else {
      this.subscribeToSaveResponse(this.quartierService.create(quartier));
    }
  }

  private createFromForm(): IQuartier {
    return {
      ...new Quartier(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      commununeId: this.editForm.get(['commununeId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IQuartier>>): void {
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

  trackById(index: number, item: ICommune): any {
    return item.id;
  }
}
