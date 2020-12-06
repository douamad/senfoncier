import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SenfoncierTestModule } from '../../../test.module';
import { CategorieBatieUpdateComponent } from 'app/entities/categorie-batie/categorie-batie-update.component';
import { CategorieBatieService } from 'app/entities/categorie-batie/categorie-batie.service';
import { CategorieBatie } from 'app/shared/model/categorie-batie.model';

describe('Component Tests', () => {
  describe('CategorieBatie Management Update Component', () => {
    let comp: CategorieBatieUpdateComponent;
    let fixture: ComponentFixture<CategorieBatieUpdateComponent>;
    let service: CategorieBatieService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SenfoncierTestModule],
        declarations: [CategorieBatieUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CategorieBatieUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CategorieBatieUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CategorieBatieService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CategorieBatie(123);
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
        const entity = new CategorieBatie();
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
