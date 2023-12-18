package com.example.gaspillezero.ui.main.PrésentationProduits

import com.example.gaspillezero.ui.main.sourceDeDonnées.DonnéesEnMémoire
import com.example.gaspillezero.ui.main.sourceDeDonnées.Produits
import com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDeDonnées
import com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDonnéesProduits
import com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDonnéesProduitsHTTP

class ProduitModèle(source : SourceDonnéesProduits =
                        SourceDonnéesProduitsHTTP("https://85cdb06a-fd31-46cc-9a92-a288f5462d7b.mock.pstmn.io/produits")) {

    private var _source : SourceDonnéesProduits = source

    suspend fun obtenirDonnéesProduit(): List<Produits>{
        return _source.obtenirDonnéesProduits()
    }
}