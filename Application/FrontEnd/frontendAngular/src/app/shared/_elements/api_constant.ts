import { environment } from 'src/environments/environment';

export const SERVEUR = `${environment.apiUrl}`;
export const CAISSE = SERVEUR + `/caisse`;
export const ADD_CAISSE = CAISSE + `/create`;
export const READ_CAISSE = CAISSE + `/read`;
export const DELETE_CAISSE = CAISSE + `/delete`;

export const AUTH = SERVEUR + `/utilisateur/login`; 
