import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SenfoncierTestModule } from '../../../test.module';
import { CategorieClotureUpdateComponent } from 'app/entities/categorie-cloture/categorie-cloture-update.component';
import { CategorieClotureService } from 'app/entities/categorie-cloture/categorie-cloture.service';
import { CategorieCloture } from 'app/shared/model/categorie-cloture.model';

describe('Component Tests', () => {
  describe('CategorieCloture Management Update Component', () => {
    let comp: CategorieClotureUpdateComponent;
    let fixture: ComponentFixture<CategorieClotureUpdateComponent>;
    let service: CategorieClotureService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SenfoncierTestModule],
        declarations: [CategorieClotureUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CategorieClotureUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CategorieClotureUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CategorieClotureService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CategorieCloture(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new CategorieCloture();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
