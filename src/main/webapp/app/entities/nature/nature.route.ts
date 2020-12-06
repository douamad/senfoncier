import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INature, Nature } from 'app/shared/model/nature.model';
import { NatureService } from './nature.service';
import { NatureComponent } from './nature.component';
import { NatureDetailComponent } from './nature-detail.component';
import { NatureUpdateComponent } from './nature-update.component';

@Injectable({ providedIn: 'root' })
export class NatureResolve implements Resolve<INature> {
  constructor(private service: NatureService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INature> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((nature: HttpResponse<Nature>) => {
          if (nature.body) {
            return of(nature.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Nature());
  }
}

export const natureRoute: Routes = [
  {
    path: '',
    component: NatureComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Natures',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: NatureDetailComponent,
    resolve: {
      nature: NatureResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Natures',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: NatureUpdateComponent,
    resolve: {
      nature: NatureResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Natures',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: NatureUpdateComponent,
    resolve: {
      nature: NatureResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Natures',
    },
    canActivate: [UserRouteAccessService],
  },
];
