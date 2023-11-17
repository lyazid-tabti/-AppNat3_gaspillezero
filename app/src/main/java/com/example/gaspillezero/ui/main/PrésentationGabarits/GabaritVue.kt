package com.example.gaspillezero.ui.main.PrésentationGabarits

import com.example.gaspillezero.ui.main.sourceDeDonnées.Gabarits

interface GabaritVue {
    fun afficherDonnées(données: List<Gabarits>)
}
