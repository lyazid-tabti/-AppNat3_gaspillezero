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
    override suspend fun obtenirDonnéesProduits(): List<Produits> {


        val produit1 = Produits(
            idProduit = 1,
            description = "Sachet de pâtes spaghetti classique",
            nom = "Pâtes spaghettini",
            date_expiration = "09/10/23",
            quantité = 14,
            prix = 0.99,
            image = "spaghettini"
        )

        val produit2 = Produits(
            idProduit = 2,
            description = "Boîte de soupe aux tomates",
            nom = "Soupe aux tomates",
            date_expiration = "09/10/23",
            quantité = 11,
            prix = 1.09,
            image = "soupetomate"
        )

        val produit3 = Produits(
            idProduit = 3,
            description = "Sac de frites surgelés coupe régulière",
            nom = "Frites surgelés",
            date_expiration = "21/10/23",
            quantité = 19,
            prix = 5.49,
            image = "frites"
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