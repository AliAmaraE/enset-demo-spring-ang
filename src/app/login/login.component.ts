import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  public loginForm! : FormGroup;
  constructor(private fd : FormBuilder,
              private authService : AuthService,
              private router : Router) {
  }
  ngOnInit() {
    this.loginForm=this.fd.group({
      username : this.fd.control(''),
      password : this.fd.control('')
    });
  }
  login(){
    let username = this.loginForm.value.username;
    let password = this.loginForm.value.password;
    let auth:boolean =this.authService.login(username,password);
    if(auth==true)
    this.router.navigateByUrl("/admin")
  }

}
