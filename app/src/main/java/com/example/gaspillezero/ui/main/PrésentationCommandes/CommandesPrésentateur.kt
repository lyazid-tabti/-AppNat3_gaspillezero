package com.example.gaspillezero.ui.main.PrésentationCommandes

import com.example.gaspillezero.ui.main.sourceDeDonnées.Commandes

interface CommandesPrésentateur {
    fun obtenirDonnées()
    fun supprimerCommande(commandes: Commandes)
}