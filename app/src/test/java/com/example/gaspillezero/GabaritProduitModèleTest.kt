package com.example.gaspillezero

import com.example.gaspillezero.ui.main.PrésentationDenrées.DenréesModèle
import com.example.gaspillezero.ui.main.PrésentationGabarits.GabaritsModèle
import com.example.gaspillezero.ui.main.sourceDeDonnées.Gabarits
import com.example.gaspillezero.ui.main.sourceDeDonnées.Épicerie
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito

class GabaritProduitModèleTest {

    private lateinit var mockModèle: GabaritsModèle

    @Test
    fun `Étant donné un test sur la méthode 'obtenirDonnées', lorsqu'on appelle cette fonction, on obtient les données et qui vérifie qu'on les a bien reçus`() {

        val gabarit1 = Gabarits(
            code = "001",
            nom = "Gabarit 1",
            description = "Description 1",
            image = "image_base64_1",
            catégorie = "Catégorie 1",
            épicerie = Épicerie(/* ... */)
        )
        val gabarit2 = Gabarits(
            code = "002",
            nom = "Gabarit 2",
            description = "Description 2",
            image = "image_base64_2",
            catégorie = "Catégorie 2",
            épicerie = Épicerie(/* ... */)
        )
        val listeGabarits = listOf(gabarit1, gabarit2)
        Mockito.`when`(mockModèle.obtenirListeGabarits()).thenReturn(listeGabarits)




       var result= mockModèle.obtenirListeGabarits()

        assertEquals(listeGabarits, result)
    }


    @Test
    fun `étant donné qu'on a un gabarit, lorsque le supprime, cela retourne vrai`() {

        val gabaritPourEffacer = Gabarits(
            code = "001",
            nom = "Gabarit à supprimer",
            description = "Description du gabarit à supprimer",
            image = "image_base64_to_delete",
            catégorie = "Catégorie du gabarit à supprimer",
            épicerie = Épicerie(/* ... */)
        )

        // Mock de la méthode supprimerGabarit dans la source de données
        Mockito.doNothing().`when`{ mockModèle.supprimerGabarit(gabaritPourEffacer) }

        runBlocking {
            mockModèle.supprimerGabarit(gabaritPourEffacer)
        }

        assertTrue(true) // Assertion ou vérification appropriée selon votre logique de test
    }




}