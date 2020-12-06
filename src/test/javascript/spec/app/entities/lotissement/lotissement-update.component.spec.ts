import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SenfoncierTestModule } from '../../../test.module';
import { LotissementUpdateComponent } from 'app/entities/lotissement/lotissement-update.component';
import { LotissementService } from 'app/entities/lotissement/lotissement.service';
import { Lotissement } from 'app/shared/model/lotissement.model';

describe('Component Tests', () => {
  describe('Lotissement Management Update Component', () => {
    let comp: LotissementUpdateComponent;
    let fixture: ComponentFixture<LotissementUpdateComponent>;
    let service: LotissementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SenfoncierTestModule],
        declarations: [LotissementUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(LotissementUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LotissementUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LotissementService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Lotissement(123);
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
        const entity = new Lotissement();
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
