import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './dashboard/home/home.component';
import { CategoryListComponent } from './entities/category/category-list/category-list.component';
import { ItemFormComponent } from './entities/item/item-form/item-form.component';
import { ItemListComponent } from './entities/item/item-list/item-list.component';
import { ItemReactiveFormComponent } from './entities/item/item-reactive-form/item-reactive-form.component';
import { AboutComponent } from './layouts/about/about.component';

const routes: Routes = [
  {path: '', component: HomeComponent, pathMatch: 'full'},
  {path: 'categories', component: CategoryListComponent},
  {path: 'nosotros', component: AboutComponent },
  {path: 'items', component: ItemListComponent},
  {path: 'categories/:categoryId/items', component: ItemListComponent},
  {path: 'items/:itemId', component:ItemFormComponent},
  { path: 'items/reactive/:itemId', component: ItemReactiveFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
