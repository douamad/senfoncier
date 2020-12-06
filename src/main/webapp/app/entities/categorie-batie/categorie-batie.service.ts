import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICategorieBatie } from 'app/shared/model/categorie-batie.model';

type EntityResponseType = HttpResponse<ICategorieBatie>;
type EntityArrayResponseType = HttpResponse<ICategorieBatie[]>;

@Injectable({ providedIn: 'root' })
export class CategorieBatieService {
  public resourceUrl = SERVER_API_URL + 'api/categorie-baties';

  constructor(protected http: HttpClient) {}

  create(categorieBatie: ICategorieBatie): Observable<EntityResponseType> {
    return this.http.post<ICategorieBatie>(this.resourceUrl, categorieBatie, { observe: 'response' });
  }

  update(categorieBatie: ICategorieBatie): Observable<EntityResponseType> {
    return this.http.put<ICategorieBatie>(this.resourceUrl, categorieBatie, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICategorieBatie>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICategorieBatie[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
