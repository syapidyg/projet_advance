import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DELETE_CAISSE, READ_CAISSE } from 'src/app/shared/_elements/api_constant';
import { CaisseRequestModel } from 'src/app/shared/_models/requests/caisse-request.model';
import { CaisseResponseModel } from 'src/app/shared/_models/responses/caisse-response.model';
import { CaisseService } from 'src/app/shared/_services/caisse-service';
import { NotificationService } from 'src/app/shared/_services/notifiaction.service';
import { TokenStorageService } from 'src/app/shared/_services/token-storage.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-caisse',
  templateUrl: './caisse.component.html',
  styleUrls: ['./caisse.component.scss']
})


export class CaisseComponent implements OnInit {



  public caisses: CaisseResponseModel[] = [];
  Swal: any;
  currentUser!: any;
  form!: FormGroup;
  formdr!: FormGroup;
  caisse!: any;
  isLoading!: boolean;
  submitted!: boolean;


  constructor(
    private caisseService: CaisseService,
    private fb: FormBuilder,
    private router: Router,
    private notif: NotificationService,
    private tokenStorage: TokenStorageService

  ) { }


  ngOnInit(): void {
    this.getCaisse();
    this.initFormLogin();
  }

  // tslint:disable-next-line: typedef
  getCaisse() {
    this.caisseService.get(READ_CAISSE).then((response: any) => {
      this.caisses = response.data;
      console.log(response);
    });
  }

  // tslint:disable-next-line: typedef
  deleteCaisse(caisse: CaisseResponseModel) {
    this.Swal.fire({
      title: 'Êtes-vous sûr?',
      text: 'Vous ne pourrez pas annuler cela!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Oui, supprimez-le!',
      cancelButtonText: 'Non, annuler'
    }).then((result: any) => {
      if (result.isConfirmed) {
          this.caisseService.delete(DELETE_CAISSE + '/' + caisse.id).then((response: any) => {
          this.caisses = response.data;
          console.log(response);
          this.getCaisse();
        });
      } else if (result.isDenied) {
          this.getCaisse();
      }
    })
  }


  // tslint:disable-next-line: typedef
  private initFormLogin() {
    this.form = this.fb.group({
      id: [null],
      nom: ['', Validators.required],
      description: ['', Validators.required]
    });
  }

  // tslint:disable-next-line: typedef
  get f() { return this.form.controls; }

  // tslint:disable-next-line: typedef
  save() {
    this.submitted = true;
    this.isLoading = true;
    if (this.form.invalid) {
      this.isLoading = !this.isLoading;
      return;
    }
    let dtoRequest;
    dtoRequest = new CaisseRequestModel(this.f.id.value, this.f.nom.value, this.f.description.value);
    console.log(dtoRequest);
    this.caisseService.post(dtoRequest).toPromise()
      .then((result: any) => {
        console.log('result', result);
        this.isLoading = !this.isLoading;
        this.notif.success('Caisse enregistré avec succès ');
        window.location.reload();
      }, err => {
        console.log(err);
        this.notif.danger('Echec lors de lenregistrement ');
        this.isLoading = !this.isLoading;
      });

  }
}
