import { FamilleResponseModel } from './famille-reponse.model';

export class ProduitResponseModel {
    constructor(
        public id: number,
        public famille: FamilleResponseModel,
        public dci: string,
        public forme: string,
        public categorie: string,
        public dosage: string,
        public rayon: string,
        public pa: number,
        public pv: number,
    ) {

    }
}