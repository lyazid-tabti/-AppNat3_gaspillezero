package com.example.gaspillezero.ui.main.PrésentationProduits

import com.example.gaspillezero.ui.main.sourceDeDonnées.Produits
import com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDeDonnées
import com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDeDonnéesHTTP

class ProduitModèle(private var _source: SourceDeDonnées = SourceDeDonnéesHTTP()) : ProduitsModèle {

    override suspend fun obtenirListeProduits(): List<Produits> = _source.obtenirDonnéesProduits()

    override suspend fun supprimerProduit(produits: Produits) {
        _source.supprimerProduit(produits)
    }

    override suspend fun modifierProduit(produits: Produits) {
        _source.modifierProduit(produits)
    }

    override suspend fun ajouterProduit(produits: Produits) {
        _source.ajouterProduit(produits)
    }
}