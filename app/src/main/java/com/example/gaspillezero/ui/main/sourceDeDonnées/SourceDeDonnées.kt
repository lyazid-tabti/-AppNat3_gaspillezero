package com.example.gaspillezero.ui.main.sourceDeDonnées

interface SourceDeDonnées {
    //suspend fun obtenirDonnéesProduits(): List<Produits>

    //suspend fun obtenirDonnéesMagasin(): List<Magasins>

    suspend fun supprimerGabarit(gabarit: Gabarits)

    suspend fun modifierGabarit(gabarit: Gabarits)

    suspend fun ajouterGabarit(gabarit: Gabarits)

    suspend fun obtenirListeGabarits() : List<Gabarits>
    suspend fun obtenirUrl(lien: String) : String

    fun obtenirDonnéesMagasin(): List<Magasins>

    suspend fun obtenirDonnéesProduits(): List<Produits>

    suspend fun obtenirDonnéesCommandes(): List<Commandes>




}