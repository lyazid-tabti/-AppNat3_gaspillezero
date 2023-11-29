package com.example.gaspillezero.ui.main.PrésentationGabarits

import com.example.gaspillezero.ui.main.sourceDeDonnées.Gabarits

interface GabaritPrésentateur {
    fun obtenirDonnées()
    fun supprimerGabarit(gabarit: Gabarits)
    fun modifierGabarit(gabarit: Gabarits)
    fun ajouterGabarit(gabarit: Gabarits)
}


