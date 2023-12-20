package com.example.gaspillezero.ui.main.PrésentationMagasin

import com.example.gaspillezero.ui.main.sourceDeDonnées.*

class MagasinModèle(private var _source: SourceDonnéesMagasin = SourceDeDonnéesMagasinHTTP()) {


    suspend fun obtenirDonnéesÉpecerie(): List<Épicerie> {
        return _source.obtenirListeÉpecerie()
    }



}