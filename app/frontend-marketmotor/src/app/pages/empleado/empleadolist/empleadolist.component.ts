import { Component } from '@angular/core';
declare function setac(): void;

@Component({
  selector: 'app-empleadolist',
  templateUrl: './empleadolist.component.html',
  styleUrls: ['./empleadolist.component.css']
})
export class EmpleadolistComponent {

  ngOnInit(): void {
    setac()
  }

}
