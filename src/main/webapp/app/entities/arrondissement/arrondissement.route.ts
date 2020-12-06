import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IArrondissement, Arrondissement } from 'app/shared/model/arrondissement.model';
import { ArrondissementService } from './arrondissement.service';
import { ArrondissementComponent } from './arrondissement.component';
import { ArrondissementDetailComponent } from './arrondissement-detail.component';
import { ArrondissementUpdateComponent } from './arrondissement-update.component';

@Injectable({ providedIn: 'root' })
export class ArrondissementResolve implements Resolve<IArrondissement> {
  constructor(private service: ArrondissementService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IArrondissement> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((arrondissement: HttpResponse<Arrondissement>) => {
          if (arrondissement.body) {
            return of(arrondissement.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Arrondissement());
  }
}

export const arrondissementRoute: Routes = [
  {
    path: '',
    component: ArrondissementComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Arrondissements',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ArrondissementDetailComponent,
    resolve: {
      arrondissement: ArrondissementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Arrondissements',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ArrondissementUpdateComponent,
    resolve: {
      arrondissement: ArrondissementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Arrondissements',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ArrondissementUpdateComponent,
    resolve: {
      arrondissement: ArrondissementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Arrondissements',
    },
    canActivate: [UserRouteAccessService],
  },
];
