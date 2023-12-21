package com.example.gaspillezero.ui.main.PrésentationDenrées

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class DenréesPrésentateur(private val vue: DenreesFragment) {

    private var iocontext : CoroutineContext = Dispatchers.IO
    var modèle = DenréesModèle()

    fun obtenirDonnées() {
        CoroutineScope( iocontext ).launch {
            val données = modèle.obtenirListeProduits()
            withContext(Dispatchers.Main) {
                vue.afficherDonnées(données)
            }
        }
    }
}
