import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ParcelleService } from 'app/entities/parcelle/parcelle.service';
import { IParcelle, Parcelle } from 'app/shared/model/parcelle.model';

describe('Service Tests', () => {
  describe('Parcelle Service', () => {
    let injector: TestBed;
    let service: ParcelleService;
    let httpMock: HttpTestingController;
    let elemDefault: IParcelle;
    let expectedResult: IParcelle | IParcelle[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ParcelleService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Parcelle(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Parcelle', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Parcelle()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Parcelle', () => {
        const returnedFromService = Object.assign(
          {
            numSection: 'BBBBBB',
            numeroParcelle: 'BBBBBB',
            nicad: 'BBBBBB',
            superfici: 1,
            titreMere: 'BBBBBB',
            titreCree: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Parcelle', () => {
        const returnedFromService = Object.assign(
          {
            numSection: 'BBBBBB',
            numeroParcelle: 'BBBBBB',
            nicad: 'BBBBBB',
            superfici: 1,
            titreMere: 'BBBBBB',
            titreCree: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Parcelle', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
