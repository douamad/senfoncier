import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SenfoncierTestModule } from '../../../test.module';
import { ProprietaireDetailComponent } from 'app/entities/proprietaire/proprietaire-detail.component';
import { Proprietaire } from 'app/shared/model/proprietaire.model';

describe('Component Tests', () => {
  describe('Proprietaire Management Detail Component', () => {
    let comp: ProprietaireDetailComponent;
    let fixture: ComponentFixture<ProprietaireDetailComponent>;
    const route = ({ data: of({ proprietaire: new Proprietaire(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SenfoncierTestModule],
        declarations: [ProprietaireDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ProprietaireDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProprietaireDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load proprietaire on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.proprietaire).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
