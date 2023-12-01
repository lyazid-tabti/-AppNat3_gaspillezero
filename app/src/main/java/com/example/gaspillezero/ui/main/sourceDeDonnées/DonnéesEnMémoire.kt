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

    override fun obtenirDonnéesProduits(): List<Produits> {

        val produit1 = Produits(
            code = "34320",
            nom = "Pâtes spaghettini",
            description = "Sachet de pâtes spaghetti classique",
            prix = 0.99,
            date_exp = "09/10/23",
            quantite_stock = 14,
            photo_url = "spaghettini"
        )

        val produit2 = Produits(
            code = "67890",
            nom = "Soupe aux tomates",
            description = "Boîte de soupe aux tomates",
            prix = 1.09,
            date_exp = "09/10/23",
            quantite_stock = 11,
            photo_url = "soupetomate"
        )

        val produit3 = Produits(
            code = "67894",
            nom = "Frites surgelés",
            description = "Sac de frites surgelés coupe régulière",
            prix = 5.49,
            date_exp = "21/10/23",
            quantite_stock = 19,
            photo_url = "frites"
        )

        liste_de_produits.add(produit1)
        liste_de_produits.add(produit2)
        liste_de_produits.add(produit3)

        return liste_de_produits
    }

    override fun obtenirDonnéesMagasin(): List<Magasins> {
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

    override suspend fun supprimerGabarit(gabarit: Gabarits){}

    override suspend fun modifierGabarit(gabarit: Gabarits){}

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