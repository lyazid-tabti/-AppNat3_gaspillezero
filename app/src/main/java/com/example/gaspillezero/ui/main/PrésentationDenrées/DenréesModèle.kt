package com.example.gaspillezero.ui.main.PrésentationDenrées

import com.example.gaspillezero.ui.main.sourceDeDonnées.*

class DenréesModèle(source: SourceDonnéesProduits =
                    SourceDonnéesProduitsHTTP("http://192.168.1.9:8080/produits")) {

    private var _source : SourceDonnéesProduits = source
    suspend fun obtenirDonnéesProduits(): List<Produits> {
        return _source.obtenirDonnéesProduits()
    }
}