package com.example.gaspillezero.ui.main.PrésentationProduits

class ProduitPrésentateur(private val vue: ProduitFragment) {

    var modèle = ProduitModèle()

    suspend fun obtenirDonnées(){
        val données = modèle.obtenirDonnéesProduit()
        vue.afficherDonnées(données)
    }
}