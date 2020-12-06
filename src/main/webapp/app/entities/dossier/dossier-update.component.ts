import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDossier, Dossier } from 'app/shared/model/dossier.model';
import { DossierService } from './dossier.service';
import { IParcelle } from 'app/shared/model/parcelle.model';
import { ParcelleService } from 'app/entities/parcelle/parcelle.service';
import { ILotissement } from 'app/shared/model/lotissement.model';
import { LotissementService } from 'app/entities/lotissement/lotissement.service';
import { INature } from 'app/shared/model/nature.model';
import { NatureService } from 'app/entities/nature/nature.service';
import { IActivite } from 'app/shared/model/activite.model';
import { ActiviteService } from 'app/entities/activite/activite.service';
import { IUsage } from 'app/shared/model/usage.model';
import { UsageService } from 'app/entities/usage/usage.service';
import { IProprietaire } from 'app/shared/model/proprietaire.model';
import { ProprietaireService } from 'app/entities/proprietaire/proprietaire.service';

type SelectableEntity = IParcelle | ILotissement | INature | IActivite | IUsage | IProprietaire;

@Component({
  selector: 'jhi-dossier-update',
  templateUrl: './dossier-update.component.html',
})
export class DossierUpdateComponent implements OnInit {
  isSaving = false;
  parcelles: IParcelle[] = [];
  lotissements: ILotissement[] = [];
  natures: INature[] = [];
  activites: IActivite[] = [];
  usages: IUsage[] = [];
  proprietaires: IProprietaire[] = [];

  editForm = this.fb.group({
    id: [],
    numero: [],
    montantLoyer: [],
    superficieBatie: [],
    coeffCorrectifBatie: [],
    valeurBatie: [],
    lineaireCloture: [],
    coeffCloture: [],
    valeurCloture: [],
    amenagementSpaciaux: [],
    valeurAmenagement: [],
    valeurVenale: [],
    valeurLocativ: [],
    paysAdresse: [],
    villeAdresse: [],
    parcelles: [],
    dossierId: [],
    natureId: [],
    activiteId: [],
    usageId: [],
    proprietaireId: [],
  });

  constructor(
    protected dossierService: DossierService,
    protected parcelleService: ParcelleService,
    protected lotissementService: LotissementService,
    protected natureService: NatureService,
    protected activiteService: ActiviteService,
    protected usageService: UsageService,
    protected proprietaireService: ProprietaireService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dossier }) => {
      this.updateForm(dossier);

      this.parcelleService.query().subscribe((res: HttpResponse<IParcelle[]>) => (this.parcelles = res.body || []));

      this.lotissementService.query().subscribe((res: HttpResponse<ILotissement[]>) => (this.lotissements = res.body || []));

      this.natureService.query().subscribe((res: HttpResponse<INature[]>) => (this.natures = res.body || []));

      this.activiteService.query().subscribe((res: HttpResponse<IActivite[]>) => (this.activites = res.body || []));

      this.usageService.query().subscribe((res: HttpResponse<IUsage[]>) => (this.usages = res.body || []));

      this.proprietaireService.query().subscribe((res: HttpResponse<IProprietaire[]>) => (this.proprietaires = res.body || []));
    });
  }

  updateForm(dossier: IDossier): void {
    this.editForm.patchValue({
      id: dossier.id,
      numero: dossier.numero,
      montantLoyer: dossier.montantLoyer,
      superficieBatie: dossier.superficieBatie,
      coeffCorrectifBatie: dossier.coeffCorrectifBatie,
      valeurBatie: dossier.valeurBatie,
      lineaireCloture: dossier.lineaireCloture,
      coeffCloture: dossier.coeffCloture,
      valeurCloture: dossier.valeurCloture,
      amenagementSpaciaux: dossier.amenagementSpaciaux,
      valeurAmenagement: dossier.valeurAmenagement,
      valeurVenale: dossier.valeurVenale,
      valeurLocativ: dossier.valeurLocativ,
      paysAdresse: dossier.paysAdresse,
      villeAdresse: dossier.villeAdresse,
      parcelles: dossier.parcelles,
      dossierId: dossier.dossierId,
      natureId: dossier.natureId,
      activiteId: dossier.activiteId,
      usageId: dossier.usageId,
      proprietaireId: dossier.proprietaireId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const dossier = this.createFromForm();
    if (dossier.id !== undefined) {
      this.subscribeToSaveResponse(this.dossierService.update(dossier));
    } else {
      this.subscribeToSaveResponse(this.dossierService.create(dossier));
    }
  }

  private createFromForm(): IDossier {
    return {
      ...new Dossier(),
      id: this.editForm.get(['id'])!.value,
      numero: this.editForm.get(['numero'])!.value,
      montantLoyer: this.editForm.get(['montantLoyer'])!.value,
      superficieBatie: this.editForm.get(['superficieBatie'])!.value,
      coeffCorrectifBatie: this.editForm.get(['coeffCorrectifBatie'])!.value,
      valeurBatie: this.editForm.get(['valeurBatie'])!.value,
      lineaireCloture: this.editForm.get(['lineaireCloture'])!.value,
      coeffCloture: this.editForm.get(['coeffCloture'])!.value,
      valeurCloture: this.editForm.get(['valeurCloture'])!.value,
      amenagementSpaciaux: this.editForm.get(['amenagementSpaciaux'])!.value,
      valeurAmenagement: this.editForm.get(['valeurAmenagement'])!.value,
      valeurVenale: this.editForm.get(['valeurVenale'])!.value,
      valeurLocativ: this.editForm.get(['valeurLocativ'])!.value,
      paysAdresse: this.editForm.get(['paysAdresse'])!.value,
      villeAdresse: this.editForm.get(['villeAdresse'])!.value,
      parcelles: this.editForm.get(['parcelles'])!.value,
      dossierId: this.editForm.get(['dossierId'])!.value,
      natureId: this.editForm.get(['natureId'])!.value,
      activiteId: this.editForm.get(['activiteId'])!.value,
      usageId: this.editForm.get(['usageId'])!.value,
      proprietaireId: this.editForm.get(['proprietaireId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDossier>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }

  getSelected(selectedVals: IParcelle[], option: IParcelle): IParcelle {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
