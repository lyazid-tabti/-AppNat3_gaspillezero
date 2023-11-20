package com.example.gaspillezero.ui.main.sourceDeDonnées

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Magasins")
class Magasins(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        @ColumnInfo(name = "magasinNom")
        val magasinNom: String,
        @ColumnInfo(name = "produitNom")
        val estDisponible: String ,

        @ColumnInfo(name = "imageID")
        val imageID: String
    )
