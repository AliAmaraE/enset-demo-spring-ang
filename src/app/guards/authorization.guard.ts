import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanActivateFn,
  Router,
  RouterStateSnapshot,
  UrlTree
} from '@angular/router';
import {Observable} from "rxjs";
import {inject, Injectable} from "@angular/core";
import {AuthService} from "../services/auth.service";
import * as repl from "repl";
@Injectable({providedIn:'root'})
export class AuthorizationGuard {
  constructor(private authService:AuthService,
              private router :Router) {
  }

  canActivate(route: ActivatedRouteSnapshot) {
   if(this.authService.isAuthenticated){
     return true;
   } else{
     this.router.navigateByUrl('/login')
     return false;
   }
  }
}
export const authGuard: CanActivateFn = (route:ActivatedRouteSnapshot,state){
  return inject(AuthorizationGuard).canActivate(route);
};
