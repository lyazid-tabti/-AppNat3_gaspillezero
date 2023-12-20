package com.example.gaspillezero.ui.main.PrésentationGabarits

import com.example.gaspillezero.ui.main.sourceDeDonnées.Gabarits
import kotlinx.coroutines.*
import kotlin.coroutines.*

class GabaritsPrésenteur(private val vue: GabaritVue, private val modèle: GabaritModèle, iocontext : CoroutineContext = Dispatchers.IO) : GabaritPrésentateur {

    private var iocontext : CoroutineContext = iocontext

    override fun obtenirDonnées() {
        CoroutineScope( iocontext ).launch {
            val données = modèle.obtenirListeGabarits()
            withContext(Dispatchers.Main) {
                vue.afficherDonnées(données)
            }
        }
    }
    override fun supprimerGabarit(gabarit: Gabarits) {
        CoroutineScope( iocontext ).launch {
            modèle.supprimerGabarit(gabarit)
        }
    }
    override fun modifierGabarit(gabarit: Gabarits) {
        CoroutineScope( iocontext ).launch {
            modèle.modifierGabarit(gabarit)
        }
    }
    override fun ajouterGabarit(gabarit: Gabarits) {
        CoroutineScope( iocontext ).launch {
            modèle.ajouterGabarit(gabarit)
        }
    }
}
