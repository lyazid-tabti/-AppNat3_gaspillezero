package com.example.gaspillezero.ui.main.sourceDeDonnées

interface SourceDeDonnées {
    fun obtenirDonnéesProduits(): List<Produits>

    fun obtenirDonnéesMagasin(): List<Magasins>


}