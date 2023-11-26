package com.example.gaspillezero.ui.main.sourceDeDonnées

import kotlinx.serialization.Serializable

@Serializable
data class Produits(
    val code: String,
    val nom: String,
    val description: String,
    val prix: Double,
    val date_exp: String,
    val quantite_stock: Int,
    val photo_url: String)
