import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IArrondissement } from 'app/shared/model/arrondissement.model';

type EntityResponseType = HttpResponse<IArrondissement>;
type EntityArrayResponseType = HttpResponse<IArrondissement[]>;

@Injectable({ providedIn: 'root' })
export class ArrondissementService {
  public resourceUrl = SERVER_API_URL + 'api/arrondissements';

  constructor(protected http: HttpClient) {}

  create(arrondissement: IArrondissement): Observable<EntityResponseType> {
    return this.http.post<IArrondissement>(this.resourceUrl, arrondissement, { observe: 'response' });
  }

  update(arrondissement: IArrondissement): Observable<EntityResponseType> {
    return this.http.put<IArrondissement>(this.resourceUrl, arrondissement, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IArrondissement>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IArrondissement[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
