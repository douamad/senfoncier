import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ILotissement, Lotissement } from 'app/shared/model/lotissement.model';
import { LotissementService } from './lotissement.service';
import { IQuartier } from 'app/shared/model/quartier.model';
import { QuartierService } from 'app/entities/quartier/quartier.service';

@Component({
  selector: 'jhi-lotissement-update',
  templateUrl: './lotissement-update.component.html',
})
export class LotissementUpdateComponent implements OnInit {
  isSaving = false;
  quartiers: IQuartier[] = [];

  editForm = this.fb.group({
    id: [],
    code: [],
    libelle: [],
    quartierId: [],
  });

  constructor(
    protected lotissementService: LotissementService,
    protected quartierService: QuartierService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ lotissement }) => {
      this.updateForm(lotissement);

      this.quartierService.query().subscribe((res: HttpResponse<IQuartier[]>) => (this.quartiers = res.body || []));
    });
  }

  updateForm(lotissement: ILotissement): void {
    this.editForm.patchValue({
      id: lotissement.id,
      code: lotissement.code,
      libelle: lotissement.libelle,
      quartierId: lotissement.quartierId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const lotissement = this.createFromForm();
    if (lotissement.id !== undefined) {
      this.subscribeToSaveResponse(this.lotissementService.update(lotissement));
    } else {
      this.subscribeToSaveResponse(this.lotissementService.create(lotissement));
    }
  }

  private createFromForm(): ILotissement {
    return {
      ...new Lotissement(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      quartierId: this.editForm.get(['quartierId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILotissement>>): void {
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

  trackById(index: number, item: IQuartier): any {
    return item.id;
  }
}
