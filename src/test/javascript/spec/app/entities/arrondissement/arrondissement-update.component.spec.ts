import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SenfoncierTestModule } from '../../../test.module';
import { ArrondissementUpdateComponent } from 'app/entities/arrondissement/arrondissement-update.component';
import { ArrondissementService } from 'app/entities/arrondissement/arrondissement.service';
import { Arrondissement } from 'app/shared/model/arrondissement.model';

describe('Component Tests', () => {
  describe('Arrondissement Management Update Component', () => {
    let comp: ArrondissementUpdateComponent;
    let fixture: ComponentFixture<ArrondissementUpdateComponent>;
    let service: ArrondissementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SenfoncierTestModule],
        declarations: [ArrondissementUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ArrondissementUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ArrondissementUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ArrondissementService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Arrondissement(123);
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
        const entity = new Arrondissement();
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
