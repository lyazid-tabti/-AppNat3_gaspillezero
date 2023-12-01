package com.example.gaspillezero.ui.main.sourceDeDonnées

interface SourceDonnéesCommandes {
    suspend fun obtenirDonnéesCommandes(): List<Commandes>
}