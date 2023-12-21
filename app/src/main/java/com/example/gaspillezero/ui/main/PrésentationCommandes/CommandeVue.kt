package com.example.gaspillezero.ui.main.PrésentationCommandes

import com.example.gaspillezero.ui.main.sourceDeDonnées.Commandes
import com.example.gaspillezero.ui.main.sourceDeDonnées.Gabarits

interface CommandeVue {
    fun afficherDonnées(données: List<Commandes>)

}