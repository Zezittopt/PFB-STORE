import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http'
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './layout/navbar/navbar.component';
import { FooterComponent } from './layout/footer/footer.component';
import { CategoryListComponent } from './entities/category/category-list/category-list.component';
import { CategoryFormComponent } from './entities/category/category-form/category-form.component';
import { ItemListComponent } from './entities/item/item-list/item-list.component';
import { ItemFormComponent } from './entities/item/item-form/item-form.component';
import { HttpRequestIntercept } from './config/interceptors/http.request-interceptor.interceptor';

import {AutoCompleteModule} from 'primeng/autocomplete';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { ItemReactiveFormComponent } from './entities/item/item-reactive-form/item-reactive-form.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    FooterComponent,
    CategoryListComponent,
    CategoryFormComponent,
    ItemListComponent,
    ItemFormComponent,
    ItemReactiveFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    AutoCompleteModule,
    BrowserAnimationsModule,
    ReactiveFormsModule
  ],
  providers: [
    { 
      provide: HTTP_INTERCEPTORS,
      useClass: HttpRequestIntercept,
      multi: true
     }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
