import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICategorieCloture } from 'app/shared/model/categorie-cloture.model';

type EntityResponseType = HttpResponse<ICategorieCloture>;
type EntityArrayResponseType = HttpResponse<ICategorieCloture[]>;

@Injectable({ providedIn: 'root' })
export class CategorieClotureService {
  public resourceUrl = SERVER_API_URL + 'api/categorie-clotures';

  constructor(protected http: HttpClient) {}

  create(categorieCloture: ICategorieCloture): Observable<EntityResponseType> {
    return this.http.post<ICategorieCloture>(this.resourceUrl, categorieCloture, { observe: 'response' });
  }

  update(categorieCloture: ICategorieCloture): Observable<EntityResponseType> {
    return this.http.put<ICategorieCloture>(this.resourceUrl, categorieCloture, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICategorieCloture>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICategorieCloture[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
