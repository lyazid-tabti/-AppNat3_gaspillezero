package com.example.gaspillezero.ui.main.DossierPanier

import androidx.room.*
import com.example.gaspillezero.ui.main.sourceDeDonnées.PanierItem

@Dao
interface PanierDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun ajouterProduit(panier: PanierItem)

    @Query("SELECT * FROM PanierItem WHERE produitNom = :nomProduit LIMIT 1")
    fun chercherProduitParNom(nomProduit: String): PanierItem?

    @Query("SELECT * FROM PanierItem")
    fun afficherItemsPanier(): MutableList<PanierItem>

    @Query("UPDATE PanierItem SET quantitéCommandé = :quantitéCommandé WHERE produitNom = :nomProduit")
    fun modifierPanierItem(quantitéCommandé: String, nomProduit: String)

    @Delete
    fun supprimerPanierItem(panier: PanierItem)
}
