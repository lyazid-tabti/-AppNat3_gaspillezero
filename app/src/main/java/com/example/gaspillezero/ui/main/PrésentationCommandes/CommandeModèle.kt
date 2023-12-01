package com.example.gaspillezero.ui.main.PrésentationCommandes

import com.example.gaspillezero.ui.main.sourceDeDonnées.Commandes
import com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDonnéesCommandes
import com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDonnéesCommandesHTTP

class CommandeModèle(source: SourceDonnéesCommandes =
                         SourceDonnéesCommandesHTTP("https://3b53d418-1355-4085-926d-24d685d2da78.mock.pstmn.io/commandes")) {

    private var _source : SourceDonnéesCommandes = source

    suspend fun obtenirDonnéesCommandes(): List<Commandes>{
        return _source.obtenirDonnéesCommandes()
    }
}