package com.example.gaspillezero.ui.main.PrésentationMagasin

import com.example.gaspillezero.ui.main.sourceDeDonnées.*

class MagasinModèle(source: SourceDonnéesMagasin = SourceDeDonnéesMagasinHTTP("https://fafc3f10-16c3-481b-b588-e33bfbfd13b9.mock.pstmn.io/magasin")) {
    private var _source: SourceDonnéesMagasin = source

    suspend fun obtenirDonnéesMagasin(): List<Magasins> {
        return _source.obtenirDonnéesMagasi()
    }

                            /*fun obtenireProduitParMagasin( products:List<Produits>, id : Int): List<Produits> {
        return _source.obtenirProduitsParMagasin(products, id)
    }*/
}