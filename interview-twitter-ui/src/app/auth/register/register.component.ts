import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {RegisterService} from '../register-service/register.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  username: string;
  name: string;
  password: string;
  form: FormGroup;

  constructor(private registerService: RegisterService, private router: Router) { }

  ngOnInit() {
    this.form = new FormGroup({
      'username': new FormControl(this.username, Validators.required),
      'name': new FormControl(this.name, Validators.required),
      'password': new FormControl(this.password, Validators.required)
    });
  }


  save(form: FormGroup) {
    this.registerService.registerUser(form.controls['username'].value, form.controls['password'].value, form.controls['name'].value);
    this.router.navigate(['/login']);
  }

  isFormSubmittedWithInvalidUsername() {
    const usernameFormControl = this.form.controls['username'];
    return this.form.touched && usernameFormControl.invalid && usernameFormControl.touched;
  }

  isFormSubmittedWithInvalidName() {
    const nameFormControl = this.form.controls['name'];
    return this.form.touched && nameFormControl.invalid && nameFormControl.touched;
  }

  isFormSubmittedWithInvalidPassword() {
    const passwordFormControl = this.form.controls['password'];
    return this.form.touched && passwordFormControl.invalid && passwordFormControl.touched;
  }
}
