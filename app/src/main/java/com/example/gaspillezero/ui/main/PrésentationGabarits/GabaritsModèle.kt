package com.example.gaspillezero.ui.main.PrésentationGabarits

import com.example.gaspillezero.ui.main.sourceDeDonnées.Gabarits
import com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDeDonnées
import com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDeDonnéesHTTP

class GabaritsModèle(private var _source: SourceDeDonnées = SourceDeDonnéesHTTP()) : GabaritModèle {
    override suspend fun obtenirListeGabarits(): List<Gabarits> = _source.obtenirListeGabarits()

    override suspend fun supprimerGabarit(gabarit: Gabarits) {
        _source.supprimerGabarit(gabarit)
    }
    override suspend fun modifierGabarit(gabarit: Gabarits) {
        _source.modifierGabarit(gabarit)
    }
    override suspend fun ajouterGabarit(gabarit: Gabarits) {
        _source.ajouterGabarit(gabarit)
    }
}
