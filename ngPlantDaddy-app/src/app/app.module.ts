
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { RegisterComponent } from './components/register/register.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from './services/auth.service';
import { SocialmediaComponent } from './components/socialmedia/socialmedia.component';
import { DatePipe } from '@angular/common';
import { BlogComponent } from './components/blog/blog.component';
import { PlantComponent } from './components/plant/plant.component';
import { SearchfilterPipe } from './pipes/searchfilter.pipe';
import { AccountComponent } from './components/account/account.component';
import { TodoComponent } from './components/todo/todo.component';
import { UserplantComponent } from './components/userplant/userplant.component';
import { SearchPlantsPipe } from './pipes/search-plants.pipe';
import { SearchPostsPipe } from './pipes/search-posts.pipe';
import { FilterPipe } from './pipes/filter.pipe';





@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    LogoutComponent,
    RegisterComponent,
    NavbarComponent,
    SocialmediaComponent,
    BlogComponent,
    PlantComponent,
    SearchfilterPipe,
    AccountComponent,
    TodoComponent,
    UserplantComponent,
    SearchPlantsPipe,
    SearchPostsPipe,
    FilterPipe,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule


  ],
  providers: [
    DatePipe,
    AuthService


  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
