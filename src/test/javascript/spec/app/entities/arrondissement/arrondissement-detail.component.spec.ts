import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SenfoncierTestModule } from '../../../test.module';
import { ArrondissementDetailComponent } from 'app/entities/arrondissement/arrondissement-detail.component';
import { Arrondissement } from 'app/shared/model/arrondissement.model';

describe('Component Tests', () => {
  describe('Arrondissement Management Detail Component', () => {
    let comp: ArrondissementDetailComponent;
    let fixture: ComponentFixture<ArrondissementDetailComponent>;
    const route = ({ data: of({ arrondissement: new Arrondissement(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SenfoncierTestModule],
        declarations: [ArrondissementDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ArrondissementDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ArrondissementDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load arrondissement on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.arrondissement).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
