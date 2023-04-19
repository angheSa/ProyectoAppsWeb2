import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ProveedorComponent } from './proveedor/proveedor.component';
import { UsuarioComponent } from './usuario/usuario.component';
import { EmpleadoComponent } from './empleado/empleado.component';
import { ProductoComponent } from './producto/producto.component';

@NgModule({
  declarations: [
    AppComponent,
    ProveedorComponent,
    UsuarioComponent,
    EmpleadoComponent,
    ProductoComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [ProveedorComponent]
})
export class AppModule { }
