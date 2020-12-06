import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDossier, Dossier } from 'app/shared/model/dossier.model';
import { DossierService } from './dossier.service';
import { DossierComponent } from './dossier.component';
import { DossierDetailComponent } from './dossier-detail.component';
import { DossierUpdateComponent } from './dossier-update.component';

@Injectable({ providedIn: 'root' })
export class DossierResolve implements Resolve<IDossier> {
  constructor(private service: DossierService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDossier> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((dossier: HttpResponse<Dossier>) => {
          if (dossier.body) {
            return of(dossier.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Dossier());
  }
}

export const dossierRoute: Routes = [
  {
    path: '',
    component: DossierComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Dossiers',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DossierDetailComponent,
    resolve: {
      dossier: DossierResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Dossiers',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DossierUpdateComponent,
    resolve: {
      dossier: DossierResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Dossiers',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DossierUpdateComponent,
    resolve: {
      dossier: DossierResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Dossiers',
    },
    canActivate: [UserRouteAccessService],
  },
];
