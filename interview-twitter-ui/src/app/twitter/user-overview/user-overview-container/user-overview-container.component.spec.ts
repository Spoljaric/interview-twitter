import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserOverviewContainerComponent } from './user-overview-container.component';

describe('UserOverviewContainerComponent', () => {
  let component: UserOverviewContainerComponent;
  let fixture: ComponentFixture<UserOverviewContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserOverviewContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserOverviewContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
