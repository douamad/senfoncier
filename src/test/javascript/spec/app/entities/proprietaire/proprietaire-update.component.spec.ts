import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SenfoncierTestModule } from '../../../test.module';
import { ProprietaireUpdateComponent } from 'app/entities/proprietaire/proprietaire-update.component';
import { ProprietaireService } from 'app/entities/proprietaire/proprietaire.service';
import { Proprietaire } from 'app/shared/model/proprietaire.model';

describe('Component Tests', () => {
  describe('Proprietaire Management Update Component', () => {
    let comp: ProprietaireUpdateComponent;
    let fixture: ComponentFixture<ProprietaireUpdateComponent>;
    let service: ProprietaireService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SenfoncierTestModule],
        declarations: [ProprietaireUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ProprietaireUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProprietaireUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProprietaireService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Proprietaire(123);
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
        const entity = new Proprietaire();
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
