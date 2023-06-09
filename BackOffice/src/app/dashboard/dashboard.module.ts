import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EntitiesModule } from '../entities/entities.module';
import { NavCategoriesComponent } from '../layouts/nav-categories/nav-categories.component';
import { CategoryComponent } from './category/category.component';
import { DashboardRoutingModule } from './dashboard.routing';
import { HomeComponent } from './home/home.component';
import { CoatsComponent } from './shop/coats/coats.component';
import { JacketsComponent } from './shop/jackets/jackets.component';
import { PantsComponent } from './shop/pants/pants.component';
import { ShopComponent } from './shop/shop.component';
import { SportsComponent } from './shop/sports/sports.component';


@NgModule({
  declarations: [
    HomeComponent,
    ShopComponent,
    CategoryComponent,
    CoatsComponent,
    JacketsComponent,
    PantsComponent,
    SportsComponent,
    NavCategoriesComponent


  ],
  imports: [
    CommonModule,
    DashboardRoutingModule,
    EntitiesModule,
    RouterModule
  ]
})
export class DashboardModule { }
