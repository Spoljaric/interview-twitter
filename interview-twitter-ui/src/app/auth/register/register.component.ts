import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';

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

  constructor() { }

  ngOnInit() {
    this.form = new FormGroup({
      'username': new FormControl(this.username, Validators.required),
      'name': new FormControl(this.name, Validators.required),
      'password': new FormControl(this.password, Validators.required)
    });
  }


  save(form: FormGroup) {
    console.log(this.form);
  }
}
