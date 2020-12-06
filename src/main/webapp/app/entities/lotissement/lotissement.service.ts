import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILotissement } from 'app/shared/model/lotissement.model';

type EntityResponseType = HttpResponse<ILotissement>;
type EntityArrayResponseType = HttpResponse<ILotissement[]>;

@Injectable({ providedIn: 'root' })
export class LotissementService {
  public resourceUrl = SERVER_API_URL + 'api/lotissements';

  constructor(protected http: HttpClient) {}

  create(lotissement: ILotissement): Observable<EntityResponseType> {
    return this.http.post<ILotissement>(this.resourceUrl, lotissement, { observe: 'response' });
  }

  update(lotissement: ILotissement): Observable<EntityResponseType> {
    return this.http.put<ILotissement>(this.resourceUrl, lotissement, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILotissement>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILotissement[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
