package com.example.gaspillezero.ui.main.sourceDeDonnées

import java.sql.Blob

data class Épicerie(
    val code: String,
    val adresse: Adresse?,
    val utilisateur: Utilisateur?,
    val nom: String,
    val courriel: String,
    val téléphone: String,
    val logo: String? //String au lieu de Blob pour l'instant (fonctionnalités non fini)
) {
}