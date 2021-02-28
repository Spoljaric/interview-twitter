import {Component, Input} from '@angular/core';
import {UserOverviewModel} from '../../../models/useroverview.model';

@Component({
  selector: 'app-user-overview-data',
  templateUrl: './user-overview-data.component.html',
  styleUrls: ['./user-overview-data.component.css']
})
export class UserOverviewDataComponent {

  @Input() userOverview: UserOverviewModel;
}
