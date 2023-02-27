import { Component, Input, OnInit } from '@angular/core';
import { READ_CAISSE } from 'src/app/shared/_elements/api_constant';
import { CaisseResponseModel } from 'src/app/shared/_models/responses/caisse-response.model';
import { CaisseService } from 'src/app/shared/_services/caisse-service';

@Component({
  selector: 'app-caisse',
  templateUrl: './caisse.component.html',
  styleUrls: ['./caisse.component.scss']
})


export class CaisseComponent implements OnInit {


  
  public caisses: CaisseResponseModel[] = [];

  constructor(
    private caisseService: CaisseService,
  ) { }

  ngOnInit(): void {
    this.getCaisse();
  }

  getCaisse() {
    this.caisseService.get(READ_CAISSE).then((response: any) => {
      this.caisses = response.data;
      console.log(response);
    })
  }
}