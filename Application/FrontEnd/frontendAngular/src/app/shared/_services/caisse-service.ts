import { Injectable } from "@angular/core";
import { HttpClient, HttpClientModule } from "@angular/common/http";
import { CaisseRequestModel } from "../_models/requests/caisse-request.model";

@Injectable({
    providedIn: 'root',
})

export class CaisseService {

    constructor(private http: HttpClient) {

    }

    public get(url: string) {
        return this.http.get(url).toPromise();
    }
    public post(url: string, dtoCaisse: CaisseRequestModel) {
        return this.http.post(url, dtoCaisse).toPromise();
    }
    public delete(url: string) {
        return this.http.delete(url).toPromise();
    }

}