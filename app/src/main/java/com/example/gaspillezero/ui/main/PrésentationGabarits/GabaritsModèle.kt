package com.example.gaspillezero.ui.main.PrésentationGabarits

import com.example.gaspillezero.ui.main.sourceDeDonnées.DonnéesEnMémoire
import com.example.gaspillezero.ui.main.sourceDeDonnées.Gabarits
import com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDeDonnées

class GabaritsModèle(private var _source: SourceDeDonnées = DonnéesEnMémoire()) : GabaritModèle {
    override fun obtenirDonnéesGabarits(): List<Gabarits> = _source.obtenirDonnéesGabarits()

    override fun supprimerGabarit(gabarit: Gabarits) {
        _source.supprimerGabarit(gabarit)
    }
    override fun modifierGabarit(gabarit: Gabarits) {
        _source.modifierGabarit(gabarit)
    }
    override fun ajouterGabarit(gabarit: Gabarits) {
        _source.ajouterGabarit(gabarit)
    }
}
