package com.example.gaspillezero.ui.main.sourceDeDonnées

import android.util.JsonWriter
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.OutputStreamWriter

class DonnéesEnMémoire : SourceDeDonnées {

    val liste_de_produits = mutableListOf<Produits>()
    val liste_de_magasin = mutableListOf<Magasins>()
    val liste_de_gabarits = mutableListOf<Gabarits>()
    val liste_de_commandes = mutableListOf<Commandes>()
    //override suspend fun obtenirDonnéesProduits(): List<Produits> {


    //   val produit1 = Produits(
    //      code = "34320",
    //      nom = "Pâtes spaghettini",
    //      description = "Sachet de pâtes spaghetti classique",
    //      prix = 0.99,
    ////      date_exp = "09/10/23",
    //    quantite_stock = 14,
    //      photo_url = "spaghettini",
    //      gabarit = null,
    //      épicerie = null
    //  )

    //  val produit2 = Produits(
    //      code = "67890",
    //      nom = "Soupe aux tomates",
    //      description = "Boîte de soupe aux tomates",
    //      prix = 1.09,
    //      date_exp = "09/10/23",
    //      quantite_stock = 11,
    //      photo_url = "soupetomate"
    //  )

    //  val produit3 = Produits(
    //      code = "67894",
    //      nom = "Frites surgelés",
    //      description = "Sac de frites surgelés coupe régulière",
    //      prix = 5.49,
    //      date_exp = "21/10/23",
    //      quantite_stock = 19,
    //      photo_url = "frites"
    //  )

        //  liste_de_produits.add(produit1)
        //liste_de_produits.add(produit2)
        //liste_de_produits.add(produit3)

    //  return liste_de_produits
    //}

    override suspend fun supprimerProduit(produits: Produits) {
        liste_de_produits.remove(produits)
    }

    override suspend fun modifierProduit(produitsModifié: Produits) {
        val index = liste_de_produits.indexOfFirst { it.code == produitsModifié.code }
        if (index != -1) {
            liste_de_produits[index] = produitsModifié
        }    }

    override suspend fun ajouterProduit(produits: Produits) {}

    override fun obtenirDonnéesMagasin(): List<Magasins> {

        val produit1 = Produits(
            code = "34320",
            nom = "Cocombre",
            description = "Sachet de pâtes spaghetti classique",
            prix = 0.99,
            date_exp = "09/10/23",
            quantite_stock = 14,
            photo_url = "spaghettini",
            //MagasinId = 1
        )

        val produit2 = Produits(
            code = "34320",
            nom = "Beurre",
            description = "Sachet de pâtes spaghetti classique",
            prix = 0.99,
            date_exp = "09/10/23",
            quantite_stock = 14,
            photo_url = "spaghettini",
            //MagasinId = 2
        )

        val produit3 = Produits(
            code = "34320",
            nom = "Oeuf",
            description = "Sachet de pâtes spaghetti classique",
            prix = 0.99,
            date_exp = "09/10/23",
            quantite_stock = 14,
            photo_url = "spaghettini",
            //MagasinId = 3
        )



        val magasin1 = Magasins(
            id = 1,
            magasinNom = "Walmart",
            estDisponible = "Disponible pour Livraison",
            imageID = "walmart"


        )

        val magasin2 = Magasins(
            id = 2,
            magasinNom = "Provigo",
            estDisponible = "Disponible pour Livraison",
            imageID = "provigo"


        )

        val magasin3 = Magasins(
            id = 3,
            magasinNom = "Maxi",
            estDisponible = "Non pour Livraison",
            imageID = "maxi"


        )
        liste_de_magasin.add(magasin1)
        liste_de_magasin.add(magasin2)
        liste_de_magasin.add(magasin3)
        return liste_de_magasin
    }

    override suspend fun obtenirDonnéesProduits(): List<Produits> {
        try {
            val client = OkHttpClient()
            val requête = Request.Builder().url("http://localhost:8080/produits").build() // Adaptez l'URL selon votre API

            val réponse = client.newCall(requête).execute()

            if (réponse.code != 200) {
                throw SourceDeDonnéesException("Erreur : ${réponse.code}")
            }
            if (réponse.body == null) {
                throw SourceDeDonnéesException("Pas de données reçues")
            }
            // Utilisation du décodeur JSON pour convertir la réponse en liste de Gabarits
            return DécodeurJson.décoderJsonVersListeProduits(réponse.body!!.string())
        } catch(e: IOException) {
            throw SourceDeDonnéesException(e.message ?: "Erreur inconnue")
        }    }

    override suspend fun ajouterGabarit(gabarit: Gabarits){}

    override suspend fun obtenirListeGabarits(): List<Gabarits> {
        try {
            val client = OkHttpClient()
            val requête = Request.Builder().url("http://localhost:8080/gabaritproduits").build() // Adaptez l'URL selon votre API

            val réponse = client.newCall(requête).execute()

            if (réponse.code != 200) {
                throw SourceDeDonnéesException("Erreur : ${réponse.code}")
            }
            if (réponse.body == null) {
                throw SourceDeDonnéesException("Pas de données reçues")
            }
            // Utilisation du décodeur JSON pour convertir la réponse en liste de Gabarits
            return DécodeurJson.décoderJsonVersListeGabarits(réponse.body!!.string())
        } catch(e: IOException) {
            throw SourceDeDonnéesException(e.message ?: "Erreur inconnue")
        }
    }

    override suspend fun obtenirDonnéesCommandes(): List<Commandes>{
        val commande1 = Commandes(
            code = 1,
            produit = "Pâtes spaghettini",
            quantite = 4
        )
        val commande2 = Commandes(
            code = 2,
            produit = "Soupes aux tomates",
            quantite = 3
        )
        val commande3 = Commandes(
            code = 3,
            produit = "Pâtes spaghettini",
            quantite = 8
        )
        val commande4 = Commandes(
            code = 4,
            produit = "Frites surgelés",
            quantite = 5
        )
        val commande5 = Commandes(
            code = 5,
            produit = "Soupes aux tomates",
            quantite = 10
        )
        liste_de_commandes.add(commande1)
        liste_de_commandes.add(commande2)
        liste_de_commandes.add(commande3)
        liste_de_commandes.add(commande4)
        liste_de_commandes.add(commande5)

        return liste_de_commandes
    }

    override suspend fun supprimerGabarit(gabarit: Gabarits) {
        liste_de_gabarits.remove(gabarit)
    }

    override suspend fun modifierGabarit(gabaritModifié: Gabarits) {
        val index = liste_de_gabarits.indexOfFirst { it.code == gabaritModifié.code }
        if (index != -1) {
            liste_de_gabarits[index] = gabaritModifié
        }
    }
    override suspend fun obtenirUrl( lien: String) : String {
        try{
            val client = OkHttpClient()

            val output = ByteArrayOutputStream()
            val writer = JsonWriter( OutputStreamWriter( output ) )

            writer.beginObject()
            writer.name("lien").value( lien )
            writer.endObject()
            writer.close()

            val body = RequestBody.create(
                "application/json".toMediaTypeOrNull(), output.toString()
            )

            val requête = Request.Builder()
                .url( "http://localhost:8080" )
                .post( body )
                .build()

            val réponse = client.newCall( requête ).execute()
            if(réponse.code == 200 ) {
                return réponse.body!!.string()
            }
            else {
                throw SourceDeDonnéesException("Code :" + réponse.code)
            }
        }
        catch(e: IOException){
            throw SourceDeDonnéesException(e.message ?: "Erreur inconnue")
        }
    }
}