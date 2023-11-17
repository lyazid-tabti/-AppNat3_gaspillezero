package com.example.gaspillezero

import com.example.gaspillezero.ui.main.PrésentationDenrées.DenréesPrésentateur
import com.example.gaspillezero.ui.main.PrésentationDenrées.DenreesFragment
import com.example.gaspillezero.ui.main.PrésentationDenrées.DenréesModèle
import com.example.gaspillezero.ui.main.sourceDeDonnées.Produits
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class DenréesPrésentateurTest {

    private lateinit var mockVue: DenreesFragment
    private lateinit var mockModèle: DenréesModèle
    private lateinit var présentateur: DenréesPrésentateur

    @Before
    fun setUp() {
        mockVue = mock(DenreesFragment::class.java)
        mockModèle = mock(DenréesModèle::class.java)
        présentateur = DenréesPrésentateur(mockVue)
        présentateur.modèle = mockModèle
    }

    @Test
    fun `Étant donné un test sur la méthode 'obtenirDonnées', lorsqu'on appelle cette fonction, on obtient les données et qui vérifie qu'on les a bien reçus`() {
        // Mise en place
        val données_fictifs = listOf(
            Produits("Produit1", "biscuits", "Boîte de biscuits", 3.99, "01/11/23", 23, "photo1"),
            Produits("Produit2", "fromage", "Sachet de fromage râpés", 5.49, "01/11/23", 18, "photo2"),
        )
        `when`(mockModèle.obtenirDonnéesProduits()).thenReturn(données_fictifs)

        // Action
        présentateur.obtenirDonnées()

        // Vérification
        verify(mockVue).afficherDonnées(données_fictifs)
    }

    @Test
    fun `Étant donné une liste d'objets de type 'Produits' non instanciée, lorsqu'on appelle la fonction 'afficherDonnées()', on obtient une liste vide`() {
        // Mise en place
        val données = emptyList<Produits>()
        `when`(mockModèle.obtenirDonnéesProduits()).thenReturn(données)

        // Action
        présentateur.obtenirDonnées()

        // Vérification
        verify(mockVue).afficherDonnées(données)
    }
}
