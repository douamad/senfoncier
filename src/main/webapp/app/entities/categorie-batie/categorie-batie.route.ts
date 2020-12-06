import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICategorieBatie, CategorieBatie } from 'app/shared/model/categorie-batie.model';
import { CategorieBatieService } from './categorie-batie.service';
import { CategorieBatieComponent } from './categorie-batie.component';
import { CategorieBatieDetailComponent } from './categorie-batie-detail.component';
import { CategorieBatieUpdateComponent } from './categorie-batie-update.component';

@Injectable({ providedIn: 'root' })
export class CategorieBatieResolve implements Resolve<ICategorieBatie> {
  constructor(private service: CategorieBatieService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICategorieBatie> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((categorieBatie: HttpResponse<CategorieBatie>) => {
          if (categorieBatie.body) {
            return of(categorieBatie.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CategorieBatie());
  }
}

export const categorieBatieRoute: Routes = [
  {
    path: '',
    component: CategorieBatieComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'CategorieBaties',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CategorieBatieDetailComponent,
    resolve: {
      categorieBatie: CategorieBatieResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'CategorieBaties',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CategorieBatieUpdateComponent,
    resolve: {
      categorieBatie: CategorieBatieResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'CategorieBaties',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CategorieBatieUpdateComponent,
    resolve: {
      categorieBatie: CategorieBatieResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'CategorieBaties',
    },
    canActivate: [UserRouteAccessService],
  },
];
