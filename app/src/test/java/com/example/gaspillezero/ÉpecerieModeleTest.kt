package com.example.gaspillezero

import com.example.gaspillezero.ui.main.PrésentationDenrées.DenreesFragment
import com.example.gaspillezero.ui.main.PrésentationDenrées.DenréesModèle
import com.example.gaspillezero.ui.main.PrésentationDenrées.DenréesPrésentateur
import com.example.gaspillezero.ui.main.PrésentationMagasin.MagasinAdapter
import com.example.gaspillezero.ui.main.PrésentationMagasin.MagasinModèle
import com.example.gaspillezero.ui.main.sourceDeDonnées.Épicerie
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class ÉpecerieModeleTest {

    private lateinit var mockVue: fragment_epecerie
    private lateinit var mockModèle: MagasinModèle

    @Before
    fun setUp() {

        mockModèle = mock(MagasinModèle::class.java)

    }


    @Test
    fun `Étant donné un test sur la méthode 'obtenirDonnéesÉpeceries', lorsqu'on appelle cette méthode, on obtient les données et qui vérifie qu'on les a bien reçus`() {
        // Mock de la dépendance _source
        val listeEpicerie: List<Épicerie> = listOf(
            Épicerie(
                code = "001",
                adresse = null,
                utilisateur = null,
                nom = "Mon Épicerie",
                courriel = "contact@monepicerie.com",
                téléphone = "123456789",
                logo = null
            ),
        val result = runBlocking { mockModèle.obtenirDonnéesÉpecerie() }
        Mockito.verify(mockVue).afficherDonnées(listeEpicerie)
    }




}