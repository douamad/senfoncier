import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProprietaire } from 'app/shared/model/proprietaire.model';

type EntityResponseType = HttpResponse<IProprietaire>;
type EntityArrayResponseType = HttpResponse<IProprietaire[]>;

@Injectable({ providedIn: 'root' })
export class ProprietaireService {
  public resourceUrl = SERVER_API_URL + 'api/proprietaires';

  constructor(protected http: HttpClient) {}

  create(proprietaire: IProprietaire): Observable<EntityResponseType> {
    return this.http.post<IProprietaire>(this.resourceUrl, proprietaire, { observe: 'response' });
  }

  update(proprietaire: IProprietaire): Observable<EntityResponseType> {
    return this.http.put<IProprietaire>(this.resourceUrl, proprietaire, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProprietaire>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProprietaire[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
