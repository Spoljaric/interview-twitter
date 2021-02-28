import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserOverviewDataComponent } from './user-overview-data.component';

describe('UserOverviewDataComponent', () => {
  let component: UserOverviewDataComponent;
  let fixture: ComponentFixture<UserOverviewDataComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserOverviewDataComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserOverviewDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
