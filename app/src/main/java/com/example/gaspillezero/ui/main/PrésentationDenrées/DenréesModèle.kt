package com.example.gaspillezero.ui.main.PrésentationDenrées

import com.example.gaspillezero.ui.main.sourceDeDonnées.*

class DenréesModèle(private var _source: SourceDeDonnées = SourceDeDonnéesHTTP()) {

    suspend fun obtenirListeProduits(): List<Produits> = _source.obtenirDonnéesProduits()
}