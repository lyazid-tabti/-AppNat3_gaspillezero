package com.example.gaspillezero.ui.main.PrésentationCommandes

import com.example.gaspillezero.ui.main.PrésentationGabarits.GabaritModèle
import com.example.gaspillezero.ui.main.PrésentationGabarits.GabaritPrésentateur
import com.example.gaspillezero.ui.main.PrésentationGabarits.GabaritVue
import com.example.gaspillezero.ui.main.sourceDeDonnées.Commandes
import com.example.gaspillezero.ui.main.sourceDeDonnées.Gabarits
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class CommandePrésentateur(private val vue: CommandeVue, private val modèle: CommandesModèle, iocontext : CoroutineContext = Dispatchers.IO) : CommandesPrésentateur {

    private var iocontext : CoroutineContext = iocontext

    override fun obtenirDonnées() {
        CoroutineScope( iocontext ).launch {
            val données = modèle.obtenirListeCommandes()
            withContext(Dispatchers.Main) {
                vue.afficherDonnées(données)
            }
        }
    }
    override fun supprimerCommande(commandes: Commandes) {
        CoroutineScope( iocontext ).launch {
            modèle.supprimerCommande(commandes)
        }
    }
}