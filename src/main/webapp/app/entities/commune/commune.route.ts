import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICommune, Commune } from 'app/shared/model/commune.model';
import { CommuneService } from './commune.service';
import { CommuneComponent } from './commune.component';
import { CommuneDetailComponent } from './commune-detail.component';
import { CommuneUpdateComponent } from './commune-update.component';

@Injectable({ providedIn: 'root' })
export class CommuneResolve implements Resolve<ICommune> {
  constructor(private service: CommuneService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICommune> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((commune: HttpResponse<Commune>) => {
          if (commune.body) {
            return of(commune.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Commune());
  }
}

export const communeRoute: Routes = [
  {
    path: '',
    component: CommuneComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Communes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CommuneDetailComponent,
    resolve: {
      commune: CommuneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Communes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CommuneUpdateComponent,
    resolve: {
      commune: CommuneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Communes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CommuneUpdateComponent,
    resolve: {
      commune: CommuneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Communes',
    },
    canActivate: [UserRouteAccessService],
  },
];
