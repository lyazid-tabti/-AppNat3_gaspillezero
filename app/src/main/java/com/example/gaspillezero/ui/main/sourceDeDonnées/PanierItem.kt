package com.example.gaspillezero.ui.main.sourceDeDonnées

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PanierItem")
class PanierItem (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "produitNom")
    val produitNom: String,
    @ColumnInfo(name = "produitPrix")
    val produitPrix: String,
    @ColumnInfo(name = "quantitéCommandé")
    val quantitéCommandé: String,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String
)