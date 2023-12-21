package com.example.gaspillezero.ui.main.sourceDeDonnées

data class Gabarits(
    val code: String,
    val nom: String,
    val description: String,
    val image: String?, //Image encodée en Base64
    val catégorie: String,
    val épicerie: Épicerie
) {
    override fun toString(): String {
        return nom
    }
}