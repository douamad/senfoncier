import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ProprietaireService } from 'app/entities/proprietaire/proprietaire.service';
import { IProprietaire, Proprietaire } from 'app/shared/model/proprietaire.model';

describe('Service Tests', () => {
  describe('Proprietaire Service', () => {
    let injector: TestBed;
    let service: ProprietaireService;
    let httpMock: HttpTestingController;
    let elemDefault: IProprietaire;
    let expectedResult: IProprietaire | IProprietaire[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ProprietaireService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Proprietaire(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Proprietaire', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Proprietaire()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Proprietaire', () => {
        const returnedFromService = Object.assign(
          {
            prenom: 'BBBBBB',
            nom: 'BBBBBB',
            raisonSocial: 'BBBBBB',
            personneMorale: true,
            lieuNaissance: 'BBBBBB',
            numCNIi: 'BBBBBB',
            ninea: 'BBBBBB',
            adresse: 'BBBBBB',
            telephone: 'BBBBBB',
            telephone2: 'BBBBBB',
            telephone3: 'BBBBBB',
            aquisition: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Proprietaire', () => {
        const returnedFromService = Object.assign(
          {
            prenom: 'BBBBBB',
            nom: 'BBBBBB',
            raisonSocial: 'BBBBBB',
            personneMorale: true,
            lieuNaissance: 'BBBBBB',
            numCNIi: 'BBBBBB',
            ninea: 'BBBBBB',
            adresse: 'BBBBBB',
            telephone: 'BBBBBB',
            telephone2: 'BBBBBB',
            telephone3: 'BBBBBB',
            aquisition: 'BBBBBB',
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

      it('should delete a Proprietaire', () => {
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
