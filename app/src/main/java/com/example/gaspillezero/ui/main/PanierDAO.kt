package com.example.gaspillezero.ui.main

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gaspillezero.ui.main.sourceDeDonnées.PanierItem

@Dao
interface PanierDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun ajouterProduit(panier: PanierItem)

    @Query("SELECT * FROM PanierItem WHERE produitNom = :nomProduit LIMIT 1")
    fun chercherProduitParID(nomProduit: String): PanierItem?

    @Query("SELECT * FROM PanierItem")
    fun afficherItemsPanier(): List<PanierItem>
}
