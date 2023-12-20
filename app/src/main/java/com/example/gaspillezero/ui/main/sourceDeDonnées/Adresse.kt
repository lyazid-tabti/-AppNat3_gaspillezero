package com.example.gaspillezero.ui.main.sourceDeDonnées

data class Adresse(
    val code: String,
    val numéro_municipal: String,
    val rue: String,
    val ville: String,
    val province: String,
    val code_postal: String,
    val pays: String ) {
}