package com.example.gaspillezero

import com.example.gaspillezero.ui.main.PrésentationDenrées.DenréesModèle
import com.example.gaspillezero.ui.main.sourceDeDonnées.Produits
import com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDeDonnées
import org.junit.Test
import org.junit.Assert.assertEquals
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class DenréesModèleTest {

    @Test
    fun `Étant donné une source de données instanciés, lorsqu'on appelle la fonction 'obtenirDonnéesProduits()', on obtient et vérifie que les deux listes dont celle instancié dans le fichier 'DonnéesEnMémoire' correspondent`() {
        // Mise en place
        val mockSource = mock(SourceDeDonnées::class.java)
        val modèle = DenréesModèle(mockSource)
        val résultat_attendu = listOf(
            Produits(
                code = "34320",
                nom = "Pâtes spaghettini",
                description = "Sachet de pâtes spaghetti classique",
                prix = 0.99,
                date_exp = "09/10/23",
                quantite_stock = 14,
                photo_url = "spaghettini",
                //MagasinId = 1
            ),
            Produits(
                code = "67890",
                nom = "Soupe aux tomates",
                description = "Boîte de soupe aux tomates",
                prix = 1.09,
                date_exp = "09/10/23",
                quantite_stock = 11,
                photo_url = "soupetomate",
                //MagasinId = 1
            )
        )
        `when`(mockSource.obtenirDonnéesProduits()).thenReturn(résultat_attendu)

        // Action
        val résultat_obtenu = modèle.obtenirDonnéesProduits()

        // Vérification
        assertEquals(résultat_attendu, résultat_obtenu)
    }

    @Test
    fun `Étant donné une liste instancié qui retourne rien, lorsqu'on appelle la fonction 'obtenirDonnéesProduits(), on obtient une liste vide`() {
        // Mise en place
        val mockSource = mock(SourceDeDonnées::class.java)
        val modèle = DenréesModèle(mockSource)
        `when`(mockSource.obtenirDonnéesProduits()).thenReturn(emptyList())

        // Action
        val résultat_obtenu = modèle.obtenirDonnéesProduits()

        // Vérification
        assertEquals(emptyList<Produits>(), résultat_obtenu)
    }
}