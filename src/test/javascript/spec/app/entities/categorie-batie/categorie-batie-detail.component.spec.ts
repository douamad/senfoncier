import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SenfoncierTestModule } from '../../../test.module';
import { CategorieBatieDetailComponent } from 'app/entities/categorie-batie/categorie-batie-detail.component';
import { CategorieBatie } from 'app/shared/model/categorie-batie.model';

describe('Component Tests', () => {
  describe('CategorieBatie Management Detail Component', () => {
    let comp: CategorieBatieDetailComponent;
    let fixture: ComponentFixture<CategorieBatieDetailComponent>;
    const route = ({ data: of({ categorieBatie: new CategorieBatie(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SenfoncierTestModule],
        declarations: [CategorieBatieDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CategorieBatieDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CategorieBatieDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load categorieBatie on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.categorieBatie).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
