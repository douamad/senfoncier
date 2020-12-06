import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICommune } from 'app/shared/model/commune.model';

type EntityResponseType = HttpResponse<ICommune>;
type EntityArrayResponseType = HttpResponse<ICommune[]>;

@Injectable({ providedIn: 'root' })
export class CommuneService {
  public resourceUrl = SERVER_API_URL + 'api/communes';

  constructor(protected http: HttpClient) {}

  create(commune: ICommune): Observable<EntityResponseType> {
    return this.http.post<ICommune>(this.resourceUrl, commune, { observe: 'response' });
  }

  update(commune: ICommune): Observable<EntityResponseType> {
    return this.http.put<ICommune>(this.resourceUrl, commune, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICommune>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICommune[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
