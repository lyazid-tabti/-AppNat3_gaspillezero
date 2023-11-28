package com.example.gaspillezero.ui.main.PrésentationProduits

import com.example.gaspillezero.ui.main.sourceDeDonnées.DonnéesEnMémoire
import com.example.gaspillezero.ui.main.sourceDeDonnées.Produits
import com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDeDonnées

class ProduitModèle(source : SourceDeDonnées = DonnéesEnMémoire()) {
    private var _source : SourceDeDonnées = source

    fun obtenirDonnéesProduit(): List<Produits>{
        return _source.obtenirDonnéesProduits()
    }
}