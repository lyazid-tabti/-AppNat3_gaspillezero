package com.example.gaspillezero.ui.main.PrésentationGabarits

import com.example.gaspillezero.ui.main.PrésentationDenrées.GabaritFragment
import com.example.gaspillezero.ui.main.sourceDeDonnées.Gabarits

class GabaritsPrésenteur(private val vue: GabaritFragment) {

    private val modèle = GabaritsModèle()

    fun obtenirDonnées() {
        val données = modèle.obtenirDonnéesGabarits()
        vue.afficherDonnées(données)
    }

    fun supprimerGabarit(gabarit: Gabarits) {
        modèle.supprimerGabarit(gabarit)
    }
}