package com.example.gaspillezero.ui.main.PrésentationMagasin

import com.example.gaspillezero.fragment_epecerie
import com.example.gaspillezero.ui.main.PrésentationDenrées.DenreesFragment
import com.example.gaspillezero.ui.main.PrésentationDenrées.DenréesModèle

class MagasinPrésentateur(private val vue: fragment_epecerie) {
    private val modèle = MagasinModèle()

    fun obtenirDonnées() {
        val données = modèle.obtenirDonnéesMagasin()
        vue.afficherDonnées(données)
    }

}