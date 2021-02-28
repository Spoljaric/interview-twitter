import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {Observable} from 'rxjs/Observable';
import {OverviewService} from '../../../services/tweet/overview.service';
import {UserOverviewModel} from '../../../models/useroverview.model';

@Component({
  selector: 'app-user-overview-view',
  templateUrl: './user-overview-view.component.html',
  styleUrls: ['./user-overview-view.component.css']
})
export class UserOverviewViewComponent implements OnInit {

  $userOverview: Observable<UserOverviewModel>;
  username: string;

  constructor(private overviewService: OverviewService, private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe((params: Params) => {
      this.username = params['username'];
      this.$userOverview = this.overviewService.fetchUserOverview(this.username);
    });
  }

}
