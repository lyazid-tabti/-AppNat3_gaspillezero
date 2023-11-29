package com.example.gaspillezero.ui.main.PrésentationGabarits

import com.example.gaspillezero.ui.main.sourceDeDonnées.Gabarits

interface GabaritModèle {
    fun obtenirDonnéesGabarits(): List<Gabarits>
    fun supprimerGabarit(gabarit: Gabarits)
    fun modifierGabarit(gabarit: Gabarits)
    fun ajouterGabarit(gabarit: Gabarits)
}

