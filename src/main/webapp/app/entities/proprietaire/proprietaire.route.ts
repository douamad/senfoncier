import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProprietaire, Proprietaire } from 'app/shared/model/proprietaire.model';
import { ProprietaireService } from './proprietaire.service';
import { ProprietaireComponent } from './proprietaire.component';
import { ProprietaireDetailComponent } from './proprietaire-detail.component';
import { ProprietaireUpdateComponent } from './proprietaire-update.component';

@Injectable({ providedIn: 'root' })
export class ProprietaireResolve implements Resolve<IProprietaire> {
  constructor(private service: ProprietaireService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProprietaire> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((proprietaire: HttpResponse<Proprietaire>) => {
          if (proprietaire.body) {
            return of(proprietaire.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Proprietaire());
  }
}

export const proprietaireRoute: Routes = [
  {
    path: '',
    component: ProprietaireComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Proprietaires',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProprietaireDetailComponent,
    resolve: {
      proprietaire: ProprietaireResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Proprietaires',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProprietaireUpdateComponent,
    resolve: {
      proprietaire: ProprietaireResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Proprietaires',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProprietaireUpdateComponent,
    resolve: {
      proprietaire: ProprietaireResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Proprietaires',
    },
    canActivate: [UserRouteAccessService],
  },
];
