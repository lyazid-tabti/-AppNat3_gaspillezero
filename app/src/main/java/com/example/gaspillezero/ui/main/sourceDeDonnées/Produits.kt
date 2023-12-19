package com.example.gaspillezero.ui.main.sourceDeDonnées

import java.util.Date


data class Produits(
    val code: String,
    val nom: String,
    val description: String,
    val prix: Double,
    val date_exp: String,
    val quantite_stock: Int,
    val photo_url: String?,
    val épicerie: Épicerie,
    val gabarit: Gabarits)
