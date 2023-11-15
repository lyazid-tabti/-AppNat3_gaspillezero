package com.example.gaspillezero.ui.main.sourceDeDonnées

import java.sql.Blob

data class Gabarits (val code: String,
                     val nom: String,
                     val description: String,
                     val image: String, //URL
                     val catégorie: String)