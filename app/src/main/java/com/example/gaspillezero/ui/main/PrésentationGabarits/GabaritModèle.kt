package com.example.gaspillezero.ui.main.PrésentationGabarits

import com.example.gaspillezero.ui.main.sourceDeDonnées.Gabarits

interface GabaritModèle {
    suspend fun obtenirListeGabarits(): List<Gabarits>
    suspend fun supprimerGabarit(gabarit: Gabarits)
    suspend fun modifierGabarit(gabarit: Gabarits)
    suspend fun ajouterGabarit(gabarit: Gabarits)
}

