import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICategorieCloture, CategorieCloture } from 'app/shared/model/categorie-cloture.model';
import { CategorieClotureService } from './categorie-cloture.service';
import { CategorieClotureComponent } from './categorie-cloture.component';
import { CategorieClotureDetailComponent } from './categorie-cloture-detail.component';
import { CategorieClotureUpdateComponent } from './categorie-cloture-update.component';

@Injectable({ providedIn: 'root' })
export class CategorieClotureResolve implements Resolve<ICategorieCloture> {
  constructor(private service: CategorieClotureService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICategorieCloture> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((categorieCloture: HttpResponse<CategorieCloture>) => {
          if (categorieCloture.body) {
            return of(categorieCloture.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CategorieCloture());
  }
}

export const categorieClotureRoute: Routes = [
  {
    path: '',
    component: CategorieClotureComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'CategorieClotures',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CategorieClotureDetailComponent,
    resolve: {
      categorieCloture: CategorieClotureResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'CategorieClotures',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CategorieClotureUpdateComponent,
    resolve: {
      categorieCloture: CategorieClotureResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'CategorieClotures',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CategorieClotureUpdateComponent,
    resolve: {
      categorieCloture: CategorieClotureResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'CategorieClotures',
    },
    canActivate: [UserRouteAccessService],
  },
];
