import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
  preserveWhitespaces: false,
})
export class NavbarComponent {

  isLoggedIn = false;

  constructor(private authService: AuthService) {
    this.isLoggedIn = this.authService.getCurrentUser() !== null;
  }

  getCurrentUser(): string {
    return this.authService.getCurrentUser();
  }

  logout() {
    return this.authService.logout();
  }
}
