
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
import { SearchPlantsPipe } from './pipes/searchplants.pipe';
import { SearchPostsPipe } from './pipes/searchposts.pipe';
import { FilterPipe } from './pipes/filter.pipe';
import { CarouselModule } from 'ngx-owl-carousel-o';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { GoogleMapsModule } from '@angular/google-maps';

import { GrowthDataComponent } from './components/growth-data/growth-data.component';
import { AboutusComponent } from './components/aboutus/aboutus.component';
import { ContactComponent } from './components/contact/contact.component';

import { FilterLightReqPipe } from './pipes/filterlightreq.pipe';
import { FilterTopicPipe } from './pipes/filtertopic.pipe';
import { FilterDatePipe } from './pipes/filterdate.pipe';










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
    GrowthDataComponent,
    AboutusComponent,
    ContactComponent,
    FilterLightReqPipe,
    FilterTopicPipe,
    FilterDatePipe,







  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    CarouselModule,
    BrowserAnimationsModule,
    GoogleMapsModule,



  ],
  providers: [
    DatePipe,
    AuthService,



  ],
  bootstrap: [AppComponent]

})
export class AppModule { }
