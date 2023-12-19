package com.example.gaspillezero.ui.main.PrésentationProduits

import com.example.gaspillezero.ui.main.sourceDeDonnées.Produits

interface ProduitVue {
    fun afficherDonnées(données: List<Produits>)
}