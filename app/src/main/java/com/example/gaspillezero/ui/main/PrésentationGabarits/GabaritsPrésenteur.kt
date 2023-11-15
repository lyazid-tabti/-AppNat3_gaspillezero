package com.example.gaspillezero.ui.main.PrésentationGabarits

import com.example.gaspillezero.ui.main.PrésentationDenrées.DenréesModèle
import com.example.gaspillezero.ui.main.PrésentationDenrées.GabaritFragment

class GabaritsPrésenteur(private val vue: GabaritFragment) {

    private val modèle = GabaritsModèle()

    fun obtenirDonnées() {
        val données = modèle.obtenirDonnéesGabarits()
        vue.afficherDonnées(données)
    }
}