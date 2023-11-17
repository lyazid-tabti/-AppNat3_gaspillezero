package com.example.gaspillezero.ui.main.PrésentationGabarits

import com.example.gaspillezero.ui.main.sourceDeDonnées.DonnéesEnMémoire
import com.example.gaspillezero.ui.main.sourceDeDonnées.Gabarits
import com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDeDonnées

class GabaritsModèle(source: SourceDeDonnées = DonnéesEnMémoire()) {

    private var _source : SourceDeDonnées = source
    fun obtenirDonnéesGabarits(): List<Gabarits> {
        return _source.obtenirDonnéesGabarits()
    }
}