package com.example.gaspillezero.ui.main.PrésentationGabarits

import com.example.gaspillezero.ui.main.sourceDeDonnées.Gabarits

class GabaritsPrésenteur(private val vue: GabaritVue) : GabaritPrésentateur {
    private val modèle = GabaritsModèle()

    override fun obtenirDonnées() {
        val données = modèle.obtenirDonnéesGabarits()
        vue.afficherDonnées(données)
    }

    override fun supprimerGabarit(gabarit: Gabarits) {
        modèle.supprimerGabarit(gabarit)
    }

    override fun modifierGabarit(gabarit: Gabarits) {
        modèle.modifierGabarit(gabarit)
    }
    override fun ajouterGabarit(gabarit: Gabarits) {
        modèle.ajouterGabarit(gabarit)
    }
}
