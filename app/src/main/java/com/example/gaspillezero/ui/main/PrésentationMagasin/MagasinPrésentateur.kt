package com.example.gaspillezero.ui.main.PrésentationMagasin

import com.example.gaspillezero.fragment_epecerie
import com.example.gaspillezero.ui.main.PrésentationDenrées.DenreesFragment
import com.example.gaspillezero.ui.main.PrésentationDenrées.DenréesModèle
import com.example.gaspillezero.ui.main.sourceDeDonnées.Produits

class MagasinPrésentateur(private val vue: fragment_epecerie) {
    private val modèle = MagasinModèle()

   suspend fun obtenirDonnées() {
        val données = modèle.obtenirDonnéesMagasin()
        vue.afficherDonnées(données)
    }

    /*fun FiltrerProduitParMagasin(products:List<Produits>, id : Int): List<Produits> {
       val liste_Produits =modèle.obtenireProduitParMagasin(products,id)

        return liste_Produits

    }*/

}