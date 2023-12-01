package com.example.gaspillezero.ui.main.PrésentationMagasin

import com.example.gaspillezero.ui.main.sourceDeDonnées.DonnéesEnMémoire
import com.example.gaspillezero.ui.main.sourceDeDonnées.Magasins
import com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDeDonnées

class MagasinModèle(source: SourceDeDonnées = DonnéesEnMémoire()) {
    private var _source : SourceDeDonnées = source

    fun obtenirDonnéesMagasin(): List<Magasins> {
        return _source.obtenirDonnéesMagasin()
    }

}