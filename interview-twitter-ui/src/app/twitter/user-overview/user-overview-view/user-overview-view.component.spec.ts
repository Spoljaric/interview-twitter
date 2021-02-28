import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserOverviewViewComponent } from './user-overview-view.component';

describe('UserOverviewViewComponent', () => {
  let component: UserOverviewViewComponent;
  let fixture: ComponentFixture<UserOverviewViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserOverviewViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserOverviewViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
