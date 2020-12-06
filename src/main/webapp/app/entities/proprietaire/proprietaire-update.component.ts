import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProprietaire, Proprietaire } from 'app/shared/model/proprietaire.model';
import { ProprietaireService } from './proprietaire.service';

@Component({
  selector: 'jhi-proprietaire-update',
  templateUrl: './proprietaire-update.component.html',
})
export class ProprietaireUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    prenom: [],
    nom: [],
    raisonSocial: [],
    personneMorale: [],
    lieuNaissance: [],
    numCNIi: [],
    ninea: [],
    adresse: [],
    telephone: [],
    telephone2: [],
    telephone3: [],
    aquisition: [],
  });

  constructor(protected proprietaireService: ProprietaireService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ proprietaire }) => {
      this.updateForm(proprietaire);
    });
  }

  updateForm(proprietaire: IProprietaire): void {
    this.editForm.patchValue({
      id: proprietaire.id,
      prenom: proprietaire.prenom,
      nom: proprietaire.nom,
      raisonSocial: proprietaire.raisonSocial,
      personneMorale: proprietaire.personneMorale,
      lieuNaissance: proprietaire.lieuNaissance,
      numCNIi: proprietaire.numCNIi,
      ninea: proprietaire.ninea,
      adresse: proprietaire.adresse,
      telephone: proprietaire.telephone,
      telephone2: proprietaire.telephone2,
      telephone3: proprietaire.telephone3,
      aquisition: proprietaire.aquisition,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const proprietaire = this.createFromForm();
    if (proprietaire.id !== undefined) {
      this.subscribeToSaveResponse(this.proprietaireService.update(proprietaire));
    } else {
      this.subscribeToSaveResponse(this.proprietaireService.create(proprietaire));
    }
  }

  private createFromForm(): IProprietaire {
    return {
      ...new Proprietaire(),
      id: this.editForm.get(['id'])!.value,
      prenom: this.editForm.get(['prenom'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      raisonSocial: this.editForm.get(['raisonSocial'])!.value,
      personneMorale: this.editForm.get(['personneMorale'])!.value,
      lieuNaissance: this.editForm.get(['lieuNaissance'])!.value,
      numCNIi: this.editForm.get(['numCNIi'])!.value,
      ninea: this.editForm.get(['ninea'])!.value,
      adresse: this.editForm.get(['adresse'])!.value,
      telephone: this.editForm.get(['telephone'])!.value,
      telephone2: this.editForm.get(['telephone2'])!.value,
      telephone3: this.editForm.get(['telephone3'])!.value,
      aquisition: this.editForm.get(['aquisition'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProprietaire>>): void {
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
