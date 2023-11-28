package com.example.gaspillezero.ui.main.PrésentationCommandes

import com.example.gaspillezero.ui.main.sourceDeDonnées.Commandes
import com.example.gaspillezero.ui.main.sourceDeDonnées.DonnéesEnMémoire
import com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDeDonnées

class CommandeModèle(source: SourceDeDonnées = DonnéesEnMémoire()) {

    private var _source : SourceDeDonnées = source

    fun obtenirDonnéesCommandes(): List<Commandes>{
        return _source.obtenirDonnéesCommandes()
    }
}