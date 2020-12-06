import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IQuartier, Quartier } from 'app/shared/model/quartier.model';
import { QuartierService } from './quartier.service';
import { QuartierComponent } from './quartier.component';
import { QuartierDetailComponent } from './quartier-detail.component';
import { QuartierUpdateComponent } from './quartier-update.component';

@Injectable({ providedIn: 'root' })
export class QuartierResolve implements Resolve<IQuartier> {
  constructor(private service: QuartierService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IQuartier> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((quartier: HttpResponse<Quartier>) => {
          if (quartier.body) {
            return of(quartier.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Quartier());
  }
}

export const quartierRoute: Routes = [
  {
    path: '',
    component: QuartierComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Quartiers',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: QuartierDetailComponent,
    resolve: {
      quartier: QuartierResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Quartiers',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: QuartierUpdateComponent,
    resolve: {
      quartier: QuartierResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Quartiers',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: QuartierUpdateComponent,
    resolve: {
      quartier: QuartierResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Quartiers',
    },
    canActivate: [UserRouteAccessService],
  },
];
