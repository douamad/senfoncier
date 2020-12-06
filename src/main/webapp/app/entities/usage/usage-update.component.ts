import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IUsage, Usage } from 'app/shared/model/usage.model';
import { UsageService } from './usage.service';

@Component({
  selector: 'jhi-usage-update',
  templateUrl: './usage-update.component.html',
})
export class UsageUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    code: [],
    libelle: [],
  });

  constructor(protected usageService: UsageService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ usage }) => {
      this.updateForm(usage);
    });
  }

  updateForm(usage: IUsage): void {
    this.editForm.patchValue({
      id: usage.id,
      code: usage.code,
      libelle: usage.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const usage = this.createFromForm();
    if (usage.id !== undefined) {
      this.subscribeToSaveResponse(this.usageService.update(usage));
    } else {
      this.subscribeToSaveResponse(this.usageService.create(usage));
    }
  }

  private createFromForm(): IUsage {
    return {
      ...new Usage(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUsage>>): void {
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
