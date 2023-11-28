package com.example.gaspillezero.ui.main.PrésentationProduits

class ProduitPrésentateur(private val vue: ProduitFragment) {

    var modèle = ProduitModèle()

    fun obtenirDonnées(){
        val données = modèle.obtenirDonnéesProduit()
        vue.afficherDonnées(données)
    }
}