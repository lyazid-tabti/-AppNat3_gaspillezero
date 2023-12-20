package com.example.gaspillezero.ui.main.PrésentationMagasin

import com.example.gaspillezero.fragment_epecerie
import com.example.gaspillezero.ui.main.PrésentationProduits.ProduitModèle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class MagasinPrésentateur(private val vue: fragment_epecerie)  {

    var modèle = MagasinModèle()




    suspend fun obtenirDonnéesÉpeceries() {
        val données = modèle.obtenirDonnéesÉpecerie()
        vue.afficherDonnées(données)


    }

    /*fun FiltrerProduitParMagasin(products:List<Produits>, id : Int): List<Produits> {
       val liste_Produits =modèle.obtenireProduitParMagasin(products,id)

        return liste_Produits

    }*/

}