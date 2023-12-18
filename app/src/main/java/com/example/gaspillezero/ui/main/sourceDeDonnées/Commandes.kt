package com.example.gaspillezero.ui.main.sourceDeDonnées

import kotlinx.serialization.Serializable

@Serializable
data class Commandes (val code: Int,
                      val produit : String,
                      val quantite: Int){
}