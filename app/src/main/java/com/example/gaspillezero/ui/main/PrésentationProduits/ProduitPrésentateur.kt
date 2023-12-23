package com.example.gaspillezero.ui.main.PrésentationProduits

import com.example.gaspillezero.ui.main.PrésentationGabarits.GabaritModèle
import com.example.gaspillezero.ui.main.PrésentationGabarits.GabaritPrésentateur
import com.example.gaspillezero.ui.main.PrésentationGabarits.GabaritVue
import com.example.gaspillezero.ui.main.sourceDeDonnées.Gabarits
import com.example.gaspillezero.ui.main.sourceDeDonnées.Produits
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class ProduitPrésentateur(private val vue: ProduitVue, var modèle: ProduitsModèle, iocontext : CoroutineContext = Dispatchers.IO) : ProduitsPrésentateur {

    private var iocontext : CoroutineContext = iocontext
    override fun obtenirDonnées() {
        CoroutineScope( iocontext ).launch {
            val données = modèle.obtenirListeProduits()
            withContext(Dispatchers.Main) {
                vue.afficherDonnées(données)
            }
        }
    }
    override fun recevoirDonnéesGabarits() {
        CoroutineScope( iocontext ).launch {
            val donnéesGabarits = modèle.recevoirDonnéesGabarits()
            withContext(Dispatchers.Main) {
                vue.recevoirDonnéesGabarits(donnéesGabarits)
            }
        }
    }
    override fun supprimerProduit(produits: Produits) {
        CoroutineScope( iocontext ).launch {
            modèle.supprimerProduit(produits)
        }
    }
    override fun modifierProduit(produits: Produits) {
        CoroutineScope( iocontext ).launch {
            modèle.modifierProduit(produits)
        }
    }
    override fun ajouterProduit(produits: Produits) {
        CoroutineScope( iocontext ).launch {
            modèle.ajouterProduit(produits)
        }
    }
}