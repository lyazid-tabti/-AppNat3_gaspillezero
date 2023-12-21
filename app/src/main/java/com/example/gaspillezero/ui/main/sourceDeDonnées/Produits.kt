package com.example.gaspillezero.ui.main.sourceDeDonnées

import kotlinx.serialization.Serializable

@Serializable
data class Produits(
    val idProduit: Int,
    val description: String,
    val nom: String,
    val date_expiration: String,
    val quantité: Int,
    val prix: Double,
    val image: String)
