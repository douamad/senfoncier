import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SenfoncierTestModule } from '../../../test.module';
import { ParcelleUpdateComponent } from 'app/entities/parcelle/parcelle-update.component';
import { ParcelleService } from 'app/entities/parcelle/parcelle.service';
import { Parcelle } from 'app/shared/model/parcelle.model';

describe('Component Tests', () => {
  describe('Parcelle Management Update Component', () => {
    let comp: ParcelleUpdateComponent;
    let fixture: ComponentFixture<ParcelleUpdateComponent>;
    let service: ParcelleService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SenfoncierTestModule],
        declarations: [ParcelleUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ParcelleUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ParcelleUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ParcelleService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Parcelle(123);
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
        const entity = new Parcelle();
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
