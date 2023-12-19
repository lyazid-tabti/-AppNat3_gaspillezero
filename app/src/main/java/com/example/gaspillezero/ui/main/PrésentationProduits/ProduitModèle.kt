package com.example.gaspillezero.ui.main.PrésentationProduits

import com.example.gaspillezero.ui.main.PrésentationGabarits.GabaritModèle
import com.example.gaspillezero.ui.main.sourceDeDonnées.DonnéesEnMémoire
import com.example.gaspillezero.ui.main.sourceDeDonnées.Gabarits
import com.example.gaspillezero.ui.main.sourceDeDonnées.Produits
import com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDeDonnées
import com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDeDonnéesHTTP
import com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDonnéesProduits
import com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDonnéesProduitsHTTP

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