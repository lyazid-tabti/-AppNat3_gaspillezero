package com.example.gaspillezero.ui.main.PrésentationDenrées

import com.example.gaspillezero.ui.main.sourceDeDonnées.*

class DenréesModèle(source: SourceDonnéesProduits =
                    SourceDonnéesProduitsHTTP("https://85cdb06a-fd31-46cc-9a92-a288f5462d7b.mock.pstmn.io/produits")) {

    private var _source : SourceDonnéesProduits = source
    suspend fun obtenirDonnéesProduits(): List<Produits> {
        return _source.obtenirDonnéesProduits()
    }
}