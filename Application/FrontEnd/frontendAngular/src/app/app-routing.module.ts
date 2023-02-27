import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { MainComponent } from './main/main.component';
import { CaisseComponent } from './pages/caisse/caisse.component';
import { LoginComponent } from './session/login/login.component';
import { UserGuardService } from './shared/_helpers/user-guard.service';

const routes: Routes = [
  { path: 'session/login', component: LoginComponent },
  {
    path: '', component: MainComponent, canActivate: [UserGuardService],
    children: [
      { path: '', component: DashboardComponent },
      {
        path: 'caisseList', component: CaisseComponent,
       
      }
    ]
  },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
