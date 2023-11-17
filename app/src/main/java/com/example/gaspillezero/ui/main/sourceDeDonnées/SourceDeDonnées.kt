package com.example.gaspillezero.ui.main.sourceDeDonnées

interface SourceDeDonnées {
    fun obtenirDonnéesProduits(): List<Produits>

    fun obtenirDonnéesGabarits(): List<Gabarits>

    fun obtenirDonnéesMagasin(): List<Magasins>
}