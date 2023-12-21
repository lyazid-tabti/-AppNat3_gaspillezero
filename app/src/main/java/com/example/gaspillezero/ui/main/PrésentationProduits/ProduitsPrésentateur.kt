package com.example.gaspillezero.ui.main.PrésentationProduits

import com.example.gaspillezero.ui.main.sourceDeDonnées.Produits

interface ProduitsPrésentateur {
    fun obtenirDonnées()
    fun recevoirDonnéesGabarits()
    fun supprimerProduit(produits: Produits)
    fun modifierProduit(produits: Produits)
    fun ajouterProduit(produits: Produits)
}