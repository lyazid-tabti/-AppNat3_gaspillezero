package com.example.gaspillezero.ui.main.PrésentationProduits

import com.example.gaspillezero.ui.main.sourceDeDonnées.Gabarits
import com.example.gaspillezero.ui.main.sourceDeDonnées.Produits

interface ProduitsModèle {
    suspend fun obtenirListeProduits(): List<Produits>
    suspend fun recevoirDonnéesGabarits(): List<Gabarits>
    suspend fun supprimerProduit(produits: Produits)
    suspend fun modifierProduit(produits: Produits)
    suspend fun ajouterProduit(produits: Produits)
}