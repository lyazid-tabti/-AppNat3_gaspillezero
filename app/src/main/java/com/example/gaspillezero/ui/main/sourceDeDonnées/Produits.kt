package com.example.gaspillezero.ui.main.sourceDeDonnées

data class Produits(val code: String,
                    val nom: String,
                    val prix: Double,
                    val date_exp: String,
                    val quantite_stock: Int,
                    val photo_url: String)
