package com.example.gaspillezero.ui.main.sourceDeDonnées

import java.sql.Blob

data class Gabarits (val code: String,
                     val nom: String,
                     val description: String,
                     val image: String?, //Image encodé en Base64
                     val catégorie: String,
                     val épicerie: Épicerie)