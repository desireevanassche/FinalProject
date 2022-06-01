import { AboutusComponent } from './components/aboutus/aboutus.component';
import { ContactComponent } from './components/contact/contact.component';
import { Growthdata } from './models/growthdata';
import { UserplantComponent } from './components/userplant/userplant.component';
import { PlantComponent } from './components/plant/plant.component';
import { AccountComponent } from './components/account/account.component';
import { SocialmediaComponent } from './components/socialmedia/socialmedia.component';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { RegisterComponent } from './components/register/register.component';
import { BlogComponent } from './components/blog/blog.component';
import { TodoComponent } from './components/todo/todo.component';
import { NgModule } from '@angular/core';


const routes: Routes = [
  // { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent },
  { path: 'socialmedia', component: SocialmediaComponent },
  { path: 'blog', component: BlogComponent },
  { path: 'account', component: AccountComponent },
  { path: 'plant', component: PlantComponent },
  { path: 'todo', component: TodoComponent },
  { path: 'userPlants', component : UserplantComponent},
  { path: 'test', component : Growthdata},
  { path: 'contact', component : ContactComponent},
  { path: 'about', component : AboutusComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
