package com.example.gaspillezero.ui.main.PrésentationCommandes

import com.example.gaspillezero.ui.main.sourceDeDonnées.Commandes
import com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDeDonnées
import com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDeDonnéesHTTP

class CommandeModèle(private var _source: SourceDeDonnées = SourceDeDonnéesHTTP()) : CommandesModèle {

    override suspend fun obtenirListeCommandes(): List<Commandes> =
        _source.obtenirListeCommandes()

    override suspend fun supprimerCommande(commandes: Commandes) {
        _source.supprimerCommande(commandes)
    }
}