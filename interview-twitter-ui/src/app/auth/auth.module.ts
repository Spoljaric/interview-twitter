import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AuthInterceptor} from './auth.interceptor';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {SharedModule} from '../shared/shared.module';
import {AuthService} from "../services/auth.service";
import {AuthGuard} from "./auth.guard";
import {LoginContainerComponent} from "./login-container/login-container.component";
import {LoginComponent} from "./login/login.component";
import {AuthRoutingModule} from "./auth-routing.module";
import {RegisterComponent} from './register/register.component';
import {RegisterService} from './register-service/register.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    AuthRoutingModule,
    HttpClientModule,
    SharedModule,
    ReactiveFormsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
    AuthGuard,
    AuthService,
    RegisterService
  ],
  declarations: [
    LoginComponent,
    LoginContainerComponent,
    RegisterComponent
  ],
})
export class AuthModule {
}
