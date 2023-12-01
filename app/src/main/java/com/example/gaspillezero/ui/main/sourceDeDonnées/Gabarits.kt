package com.example.gaspillezero.ui.main.sourceDeDonnées

import java.sql.Blob

data class Gabarits (val code: String,
                     val nom: String,
                     val description: String,
                     val image: String?, //nom de l'image pour l'instant (fonctionnalités non fini)
                     val catégorie: String,
                     val épicerie: Épicerie)