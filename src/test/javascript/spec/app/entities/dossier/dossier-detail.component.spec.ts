import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SenfoncierTestModule } from '../../../test.module';
import { DossierDetailComponent } from 'app/entities/dossier/dossier-detail.component';
import { Dossier } from 'app/shared/model/dossier.model';

describe('Component Tests', () => {
  describe('Dossier Management Detail Component', () => {
    let comp: DossierDetailComponent;
    let fixture: ComponentFixture<DossierDetailComponent>;
    const route = ({ data: of({ dossier: new Dossier(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SenfoncierTestModule],
        declarations: [DossierDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DossierDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DossierDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load dossier on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dossier).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
