package com.example.gaspillezero.ui.main.PrésentationDenrées

import com.example.gaspillezero.ui.main.sourceDeDonnées.DonnéesEnMémoire
import com.example.gaspillezero.ui.main.sourceDeDonnées.Produits
import com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDeDonnées

class DenréesModèle(source: SourceDeDonnées = DonnéesEnMémoire()) {

    private var _source : SourceDeDonnées = source
    fun obtenirDonnéesProduits(): List<Produits> {
        return _source.obtenirDonnéesProduits()
    }
}