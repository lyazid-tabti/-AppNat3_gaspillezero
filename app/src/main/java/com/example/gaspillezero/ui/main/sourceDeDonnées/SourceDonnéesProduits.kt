package com.example.gaspillezero.ui.main.sourceDeDonnées

interface SourceDonnéesProduits {
    suspend fun obtenirDonnéesProduits(): List<Produits>
}