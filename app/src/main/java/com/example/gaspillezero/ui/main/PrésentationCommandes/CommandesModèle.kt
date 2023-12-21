package com.example.gaspillezero.ui.main.PrésentationCommandes

import com.example.gaspillezero.ui.main.sourceDeDonnées.Commandes
import com.example.gaspillezero.ui.main.sourceDeDonnées.Gabarits

interface CommandesModèle {
    suspend fun obtenirListeCommandes(): List<Commandes>
    suspend fun supprimerCommande(commandes: Commandes)
}