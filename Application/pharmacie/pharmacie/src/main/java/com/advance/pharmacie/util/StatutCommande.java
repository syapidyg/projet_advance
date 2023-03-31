package com.advance.pharmacie.util;

public enum StatutCommande {

    NON_REGLE(0,"non regle"),
    REGLE(1, "regle"),
    EN_COURS(2, "En cours"),
    EN_ATTENTE(3, "En attente"),
    ENTREE(4, "Terminé"),
    SORTIE(5, "Sortie de stock"),
    TRANSFERT(6, "transfert effectué");
    String valeur;
    int cle;

    StatutCommande(int i, String s) {
    }



    }
