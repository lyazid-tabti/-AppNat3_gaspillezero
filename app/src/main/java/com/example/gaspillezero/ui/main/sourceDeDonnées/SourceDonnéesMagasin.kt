package com.example.gaspillezero.ui.main.sourceDeDonnées

import com.example.gaspillezero.ui.main.Exception.SourceDeDonnéesException

interface SourceDonnéesMagasin {





    suspend fun obtenirUrl(lien: String): String

    suspend fun obtenirListeÉpecerie(): List<Épicerie>
}