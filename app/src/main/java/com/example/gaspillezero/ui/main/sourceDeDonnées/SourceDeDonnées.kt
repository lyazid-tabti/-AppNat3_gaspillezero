package com.example.gaspillezero.ui.main.sourceDeDonnées

interface SourceDeDonnées {
    suspend fun obtenirDonnéesProduits(): List<Produits>

    fun obtenirDonnéesGabarits(): List<Gabarits>

    fun obtenirDonnéesMagasin(): List<Magasins>

    suspend fun obtenirDonnéesCommandes(): List<Commandes>

    fun supprimerGabarit(gabarit: Gabarits)
}