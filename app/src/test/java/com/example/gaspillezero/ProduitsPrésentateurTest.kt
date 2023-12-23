package com.example.gaspillezero

import com.example.gaspillezero.ui.main.PrésentationProduits.ProduitAdapter
import com.example.gaspillezero.ui.main.PrésentationProduits.ProduitFragment
import com.example.gaspillezero.ui.main.PrésentationProduits.ProduitModèle
import com.example.gaspillezero.ui.main.PrésentationProduits.ProduitPrésentateur
import com.example.gaspillezero.ui.main.PrésentationProduits.ProduitVue
import com.example.gaspillezero.ui.main.sourceDeDonnées.Adresse
import com.example.gaspillezero.ui.main.sourceDeDonnées.Gabarits
import com.example.gaspillezero.ui.main.sourceDeDonnées.Produits
import com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDeDonnées
import com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDeDonnéesHTTP
import com.example.gaspillezero.ui.main.sourceDeDonnées.Utilisateur
import com.example.gaspillezero.ui.main.sourceDeDonnées.Épicerie
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class ProduitsPrésentateurTest {

    private lateinit var mockVue: ProduitFragment
    private lateinit var mockModèle: ProduitModèle
    private lateinit var présentateur: ProduitPrésentateur
    private lateinit var mockVues: ProduitVue
    private lateinit var mockAdapter: ProduitAdapter
    private var _mockSource: SourceDeDonnées = SourceDeDonnéesHTTP()
    @Before
    fun setUp() {
        mockVues = mock(ProduitVue::class.java)
        mockVue = mock(ProduitFragment::class.java)
        mockModèle = mock(ProduitModèle::class.java)
        présentateur = ProduitPrésentateur(mockVues,mockModèle)
        présentateur.modèle = mockModèle
        Dispatchers.setMain(Dispatchers.Unconfined) // Use Unconfined dispatcher for testing
        // Use Unconfined dispatcher for testing
    }
    @Test
     fun `Étant donné un test sur la méthode 'obtenirDonnées', lorsqu'on appelle cette fonction, on obtient les données et qui vérifie qu'on les a bien reçus`() {
        // Mise en place
        val mockAdresse = Adresse("1","1","Linton","Quebec","Montreal","K9Y 6S1","Canada")
        val mockUti = Utilisateur("1","Montplaisir","Samuel","mont@gmail.com",mockAdresse ,"5143645412","Client")
        val épicerie_fictifs = listOf(Épicerie("1",mockAdresse,mockUti,"TGI FRIDAYS","tgi@gmail.com","5140964616","TGI"))
        val mockGabarits = Gabarits("1","Biscuit","Dry bread","biscuit.png","Collation",épicerie_fictifs[0])
        val données_fictifs = listOf(
            Produits("3", "biscuits", "Boîte de biscuits", 3.99, "01/11/23", 23, "photo1",épicerie_fictifs[0],mockGabarits),
            Produits("Produit2", "fromage", "Sachet de fromage râpés", 5.49, "01/11/23", 18, "photo2",épicerie_fictifs[0],mockGabarits),
        )
        //Mockito.`when`(présentateur.obtenirDonnées()).thenReturn()
        runBlocking {
            Mockito.`when`(mockModèle.obtenirListeProduits()).thenReturn(données_fictifs)
        }

        // Action
        présentateur.obtenirDonnées()

        // Vérification
        Mockito.verify(mockVue).afficherDonnées(données_fictifs)
    }
}