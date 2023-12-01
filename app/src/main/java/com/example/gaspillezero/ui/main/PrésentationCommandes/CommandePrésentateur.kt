package com.example.gaspillezero.ui.main.PrésentationCommandes

class CommandePrésentateur(private val vue: CommandeFragment) {

    var modèle = CommandeModèle()

    suspend fun obtenirDonnées(){
        val données = modèle.obtenirDonnéesCommandes()
        vue.afficherDonnées(données)
    }
}