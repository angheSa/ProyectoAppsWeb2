import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductolistComponent } from './pages/producto/productolist/productolist.component';

const routes: Routes = [
  { path: 'productos', component: ProductolistComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }