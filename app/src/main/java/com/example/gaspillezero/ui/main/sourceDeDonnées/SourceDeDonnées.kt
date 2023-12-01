package com.example.gaspillezero.ui.main.sourceDeDonnées

interface SourceDeDonnées {
    suspend fun obtenirDonnéesProduits(): List<Produits>

    fun obtenirDonnéesGabarits(): List<Gabarits>

    fun obtenirDonnéesMagasin(): List<Magasins>

    fun supprimerGabarit(gabarit: Gabarits)

    fun modifierGabarit(gabarit: Gabarits)

    fun ajouterGabarit(gabarit: Gabarits)


    //fun obtenirProduitsParMagasin(produits: List<Produits>, magasinId: Int): List<Produits>
}