package com.example.gaspillezero.ui.main.PrésentationDenrées

class DenréesPrésentateur(private val vue: DenreesFragment) {

    private val modèle = DenréesModèle()

    fun obtenirDonnées() {
        val données = modèle.obtenirDonnéesProduits()
        vue.afficherDonnées(données)
    }
}
