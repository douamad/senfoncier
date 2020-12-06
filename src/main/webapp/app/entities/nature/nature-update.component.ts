import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { INature, Nature } from 'app/shared/model/nature.model';
import { NatureService } from './nature.service';

@Component({
  selector: 'jhi-nature-update',
  templateUrl: './nature-update.component.html',
})
export class NatureUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    code: [],
    libelle: [],
  });

  constructor(protected natureService: NatureService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ nature }) => {
      this.updateForm(nature);
    });
  }

  updateForm(nature: INature): void {
    this.editForm.patchValue({
      id: nature.id,
      code: nature.code,
      libelle: nature.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const nature = this.createFromForm();
    if (nature.id !== undefined) {
      this.subscribeToSaveResponse(this.natureService.update(nature));
    } else {
      this.subscribeToSaveResponse(this.natureService.create(nature));
    }
  }

  private createFromForm(): INature {
    return {
      ...new Nature(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INature>>): void {
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
