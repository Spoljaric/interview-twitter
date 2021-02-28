import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserOverviewContainerComponent } from './user-overview-container/user-overview-container.component';
import { UserOverviewDataComponent } from './user-overview-data/user-overview-data.component';
import { UserOverviewViewComponent } from './user-overview-view/user-overview-view.component';
import {RouterModule} from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: '', component: UserOverviewContainerComponent, children: [
          {path: '', component: UserOverviewViewComponent},
        ],
      },
    ]),
    CommonModule,
    RouterModule
  ],
  declarations: [UserOverviewContainerComponent, UserOverviewDataComponent, UserOverviewViewComponent]
})
export class UserOverviewModule { }
