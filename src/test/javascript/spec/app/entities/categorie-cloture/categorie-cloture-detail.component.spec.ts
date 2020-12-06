import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SenfoncierTestModule } from '../../../test.module';
import { CategorieClotureDetailComponent } from 'app/entities/categorie-cloture/categorie-cloture-detail.component';
import { CategorieCloture } from 'app/shared/model/categorie-cloture.model';

describe('Component Tests', () => {
  describe('CategorieCloture Management Detail Component', () => {
    let comp: CategorieClotureDetailComponent;
    let fixture: ComponentFixture<CategorieClotureDetailComponent>;
    const route = ({ data: of({ categorieCloture: new CategorieCloture(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SenfoncierTestModule],
        declarations: [CategorieClotureDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CategorieClotureDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CategorieClotureDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load categorieCloture on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.categorieCloture).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
