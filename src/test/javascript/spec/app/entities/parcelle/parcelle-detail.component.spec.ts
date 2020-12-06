import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SenfoncierTestModule } from '../../../test.module';
import { ParcelleDetailComponent } from 'app/entities/parcelle/parcelle-detail.component';
import { Parcelle } from 'app/shared/model/parcelle.model';

describe('Component Tests', () => {
  describe('Parcelle Management Detail Component', () => {
    let comp: ParcelleDetailComponent;
    let fixture: ComponentFixture<ParcelleDetailComponent>;
    const route = ({ data: of({ parcelle: new Parcelle(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SenfoncierTestModule],
        declarations: [ParcelleDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ParcelleDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ParcelleDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load parcelle on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.parcelle).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
