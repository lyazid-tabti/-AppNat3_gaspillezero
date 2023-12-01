package com.example.gaspillezero.ui.main.sourceDeDonnées

import com.example.gaspillezero.ui.main.Exception.SourceDeDonnéesException

interface SourceDonnéesMagasin {


    suspend fun obtenirDonnéesMagasi(): List<Magasins>
}