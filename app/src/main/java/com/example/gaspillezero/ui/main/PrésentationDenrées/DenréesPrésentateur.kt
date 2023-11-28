package com.example.gaspillezero.ui.main.PrésentationDenrées

class DenréesPrésentateur(private val vue: DenreesFragment) {

    var modèle = DenréesModèle()

    suspend fun obtenirDonnées() {
        val données = modèle.obtenirDonnéesProduits()
        vue.afficherDonnées(données)
    }
}
