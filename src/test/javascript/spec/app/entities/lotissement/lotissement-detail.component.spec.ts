import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SenfoncierTestModule } from '../../../test.module';
import { LotissementDetailComponent } from 'app/entities/lotissement/lotissement-detail.component';
import { Lotissement } from 'app/shared/model/lotissement.model';

describe('Component Tests', () => {
  describe('Lotissement Management Detail Component', () => {
    let comp: LotissementDetailComponent;
    let fixture: ComponentFixture<LotissementDetailComponent>;
    const route = ({ data: of({ lotissement: new Lotissement(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SenfoncierTestModule],
        declarations: [LotissementDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(LotissementDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LotissementDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load lotissement on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.lotissement).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
