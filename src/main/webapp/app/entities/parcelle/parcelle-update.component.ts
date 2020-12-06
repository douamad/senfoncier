import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IParcelle, Parcelle } from 'app/shared/model/parcelle.model';
import { ParcelleService } from './parcelle.service';

@Component({
  selector: 'jhi-parcelle-update',
  templateUrl: './parcelle-update.component.html',
})
export class ParcelleUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    numSection: [],
    numeroParcelle: [],
    nicad: [],
    superfici: [],
    titreMere: [],
    titreCree: [],
  });

  constructor(protected parcelleService: ParcelleService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ parcelle }) => {
      this.updateForm(parcelle);
    });
  }

  updateForm(parcelle: IParcelle): void {
    this.editForm.patchValue({
      id: parcelle.id,
      numSection: parcelle.numSection,
      numeroParcelle: parcelle.numeroParcelle,
      nicad: parcelle.nicad,
      superfici: parcelle.superfici,
      titreMere: parcelle.titreMere,
      titreCree: parcelle.titreCree,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const parcelle = this.createFromForm();
    if (parcelle.id !== undefined) {
      this.subscribeToSaveResponse(this.parcelleService.update(parcelle));
    } else {
      this.subscribeToSaveResponse(this.parcelleService.create(parcelle));
    }
  }

  private createFromForm(): IParcelle {
    return {
      ...new Parcelle(),
      id: this.editForm.get(['id'])!.value,
      numSection: this.editForm.get(['numSection'])!.value,
      numeroParcelle: this.editForm.get(['numeroParcelle'])!.value,
      nicad: this.editForm.get(['nicad'])!.value,
      superfici: this.editForm.get(['superfici'])!.value,
      titreMere: this.editForm.get(['titreMere'])!.value,
      titreCree: this.editForm.get(['titreCree'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IParcelle>>): void {
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
}
