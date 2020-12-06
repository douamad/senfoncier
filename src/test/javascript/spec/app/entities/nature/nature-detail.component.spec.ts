import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SenfoncierTestModule } from '../../../test.module';
import { NatureDetailComponent } from 'app/entities/nature/nature-detail.component';
import { Nature } from 'app/shared/model/nature.model';

describe('Component Tests', () => {
  describe('Nature Management Detail Component', () => {
    let comp: NatureDetailComponent;
    let fixture: ComponentFixture<NatureDetailComponent>;
    const route = ({ data: of({ nature: new Nature(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SenfoncierTestModule],
        declarations: [NatureDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(NatureDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NatureDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load nature on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.nature).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
