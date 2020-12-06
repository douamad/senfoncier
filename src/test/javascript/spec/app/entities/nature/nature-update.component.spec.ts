import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SenfoncierTestModule } from '../../../test.module';
import { NatureUpdateComponent } from 'app/entities/nature/nature-update.component';
import { NatureService } from 'app/entities/nature/nature.service';
import { Nature } from 'app/shared/model/nature.model';

describe('Component Tests', () => {
  describe('Nature Management Update Component', () => {
    let comp: NatureUpdateComponent;
    let fixture: ComponentFixture<NatureUpdateComponent>;
    let service: NatureService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SenfoncierTestModule],
        declarations: [NatureUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(NatureUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NatureUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NatureService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Nature(123);
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
        const entity = new Nature();
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
