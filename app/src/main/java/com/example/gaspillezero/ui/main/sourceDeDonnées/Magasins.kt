package com.example.gaspillezero.ui.main.sourceDeDonnées

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Serializable
data class Magasins(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        @ColumnInfo(name = "magasinNom")
        val magasinNom: String,
        @ColumnInfo(name = "produitNom")
        val estDisponible: String ,

        @ColumnInfo(name = "imageID")
        val imageID: String,

        //val produits: MutableList<Produits>




    )
