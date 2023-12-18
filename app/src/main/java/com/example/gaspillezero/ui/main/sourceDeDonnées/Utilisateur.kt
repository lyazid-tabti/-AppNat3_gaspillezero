package com.example.gaspillezero.ui.main.sourceDeDonnées

data class Utilisateur(
    val code: String,
    val nom: String,
    val prénom: String,
    val courriel: String,
    val adresse: Adresse?,
    val téléphone: String,
    val rôle: String) {
}