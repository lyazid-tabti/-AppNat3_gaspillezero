package com.example.gaspillezero.ui.main.sourceDeDonnées

import android.util.JsonWriter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import okio.IOException
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.OutputStreamWriter

class SourceDeDonnéesHTTP(): SourceDeDonnées {

    //url_chargement : String

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
                .url( "http://10.0.2.2:8080" )
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


    @Throws(SourceDeDonnéesException::class)
    override suspend fun obtenirListeGabarits(): List<Gabarits> {
        try {
            val client = OkHttpClient()

            // Je n'ai pas encore de méthode pour obtenir le token donc voici un token avec les autorisations pour s'authentifier
            val token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IllUa05idDBNQjhfWm0ydGM4aGlPNyJ9.eyJpc3MiOiJodHRwczovL2Rldi10bXN5bGhjcW15bDYzbHJ5LnVzLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw2NTZlMjJkYTM0NDA4ZTczMWMzYjAxNTMiLCJhdWQiOiJodHRwOi8vZ2FzcGlsbGFnZXplcm8uZGVtbyIsImlhdCI6MTcwMjk2MjYyMSwiZXhwIjoxNzAzMDQ5MDIxLCJhenAiOiJjaXRFaVZDTWZQeW54SjQzOWZ6cGt2a3l4OHlqTE9sZCIsImd0eSI6InBhc3N3b3JkIn0.X7KHgbdc2-tNrayrh7FqaPQSjlG0UCHSE1tkIpkz82QOrzj6HZFl0fEDytoX2_gmloBI_SygUs0FyfyMPHhu6n9nDd_r8pGheClbI62z7xCgOQuw7X9ivat2MEY2UDfCMo4Q1XUr700jaAwv5lI-XSsM-r8unvoxms7TwJ6lS8fZXRriBISwTDGedqBy6KbUmn9cZHKNhIdRdqFfO5eDztIByQAfnxSx6molTolsysX3lKTL2pL7gNMhYEDFiLcRgGcwWTJarVoaspDOYBL5JjjwyAht6TLEjkiT3EELcWjkCuMI2lN0dhsu6wKTQ7vH9qJ1UlxF1IDAQtwolnk1wQ"

            val requête = Request.Builder()
                .url("http://10.0.2.2:8080/gabaritproduits")
                .addHeader("Authorization", "Bearer $token") // Ajout de l'en-tête d'authentification
                .build()

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

    @Throws(SourceDeDonnéesException::class)
    override suspend fun supprimerGabarit(gabarit: Gabarits) {
        try {
            val client = OkHttpClient()
            val token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IllUa05idDBNQjhfWm0ydGM4aGlPNyJ9.eyJpc3MiOiJodHRwczovL2Rldi10bXN5bGhjcW15bDYzbHJ5LnVzLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw2NTZlMjJkYTM0NDA4ZTczMWMzYjAxNTMiLCJhdWQiOiJodHRwOi8vZ2FzcGlsbGFnZXplcm8uZGVtbyIsImlhdCI6MTcwMjk2MjYyMSwiZXhwIjoxNzAzMDQ5MDIxLCJhenAiOiJjaXRFaVZDTWZQeW54SjQzOWZ6cGt2a3l4OHlqTE9sZCIsImd0eSI6InBhc3N3b3JkIn0.X7KHgbdc2-tNrayrh7FqaPQSjlG0UCHSE1tkIpkz82QOrzj6HZFl0fEDytoX2_gmloBI_SygUs0FyfyMPHhu6n9nDd_r8pGheClbI62z7xCgOQuw7X9ivat2MEY2UDfCMo4Q1XUr700jaAwv5lI-XSsM-r8unvoxms7TwJ6lS8fZXRriBISwTDGedqBy6KbUmn9cZHKNhIdRdqFfO5eDztIByQAfnxSx6molTolsysX3lKTL2pL7gNMhYEDFiLcRgGcwWTJarVoaspDOYBL5JjjwyAht6TLEjkiT3EELcWjkCuMI2lN0dhsu6wKTQ7vH9qJ1UlxF1IDAQtwolnk1wQ"
            val url = "http://10.0.2.2:8080/gabaritproduit/${gabarit.code}"

            val requête = Request.Builder()
                .url(url)
                .delete() // Requête DELETE
                .addHeader("Authorization", "Bearer $token")
                .build()

            val réponse = client.newCall(requête).execute()

            if (réponse.code != 200) {
                throw SourceDeDonnéesException("Erreur : ${réponse.code}")
            }
            if (réponse.body == null) {
                throw SourceDeDonnéesException("Pas de données reçues")
            }
        } catch(e: IOException) {
            throw SourceDeDonnéesException("Erreur réseau: ${e.message}")
        }
    }

    override suspend fun modifierGabarit(gabarit: Gabarits) {
        try {
            val client = OkHttpClient()
            val token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IllUa05idDBNQjhfWm0ydGM4aGlPNyJ9.eyJpc3MiOiJodHRwczovL2Rldi10bXN5bGhjcW15bDYzbHJ5LnVzLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw2NTZlMjJkYTM0NDA4ZTczMWMzYjAxNTMiLCJhdWQiOiJodHRwOi8vZ2FzcGlsbGFnZXplcm8uZGVtbyIsImlhdCI6MTcwMjk2MjYyMSwiZXhwIjoxNzAzMDQ5MDIxLCJhenAiOiJjaXRFaVZDTWZQeW54SjQzOWZ6cGt2a3l4OHlqTE9sZCIsImd0eSI6InBhc3N3b3JkIn0.X7KHgbdc2-tNrayrh7FqaPQSjlG0UCHSE1tkIpkz82QOrzj6HZFl0fEDytoX2_gmloBI_SygUs0FyfyMPHhu6n9nDd_r8pGheClbI62z7xCgOQuw7X9ivat2MEY2UDfCMo4Q1XUr700jaAwv5lI-XSsM-r8unvoxms7TwJ6lS8fZXRriBISwTDGedqBy6KbUmn9cZHKNhIdRdqFfO5eDztIByQAfnxSx6molTolsysX3lKTL2pL7gNMhYEDFiLcRgGcwWTJarVoaspDOYBL5JjjwyAht6TLEjkiT3EELcWjkCuMI2lN0dhsu6wKTQ7vH9qJ1UlxF1IDAQtwolnk1wQ"
            val json = JSONObject().apply {
                put("idGabaritProduit", gabarit.code.toInt())
                put("nom", gabarit.nom)
                put("description", gabarit.description)
                put("image", gabarit.image)
                put("categorie", gabarit.catégorie)
                put("épicerie", JSONObject().apply {
                    put("idÉpicerie", gabarit.épicerie.code.toInt())
                    put("adresse", JSONObject().apply {
                        put("idAdresse", gabarit.épicerie.adresse?.code?.toInt())
                        put("numéro_municipal", gabarit.épicerie.adresse?.numéro_municipal)
                        put("rue", gabarit.épicerie.adresse?.rue)
                        put("ville", gabarit.épicerie.adresse?.ville)
                        put("province", gabarit.épicerie.adresse?.province)
                        put("code_postal", gabarit.épicerie.adresse?.code_postal)
                        put("pays", gabarit.épicerie.adresse?.pays)
                    })
                    put("utilisateur", JSONObject().apply {
                        put("code", gabarit.épicerie.utilisateur?.code?.toInt())
                        put("nom", gabarit.épicerie.utilisateur?.nom)
                        put("prénom", gabarit.épicerie.utilisateur?.prénom)
                        put("courriel", gabarit.épicerie.utilisateur?.courriel)
                        put("adresse", JSONObject().apply {
                            put("idAdresse", gabarit.épicerie.utilisateur?.adresse?.code?.toInt())
                            put("numéro_municipal", gabarit.épicerie.utilisateur?.adresse?.numéro_municipal)
                            put("rue", gabarit.épicerie.utilisateur?.adresse?.rue)
                            put("ville", gabarit.épicerie.utilisateur?.adresse?.ville)
                            put("province", gabarit.épicerie.utilisateur?.adresse?.province)
                            put("code_postal", gabarit.épicerie.utilisateur?.adresse?.code_postal)
                            put("pays", gabarit.épicerie.utilisateur?.adresse?.pays)
                        })
                        put("téléphone", gabarit.épicerie.utilisateur?.téléphone)
                        put("rôle", gabarit.épicerie.utilisateur?.rôle)
                        put("tokenAuth0", "")
                    })
                    put("nom", gabarit.épicerie.nom)
                    put("courriel", gabarit.épicerie.courriel)
                    put("téléphone", gabarit.épicerie.téléphone)
                    put("logo", gabarit.épicerie.logo)
                })
            }

            val requestBody = json.toString().toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

            val requête = Request.Builder()
                .url("http://10.0.2.2:8080/gabaritproduit/${gabarit.code}")
                .addHeader("Authorization", "Bearer $token")
                .put(requestBody)
                .build()

            val réponse = client.newCall(requête).execute()

            if (réponse.code != 200) {
                throw SourceDeDonnéesException("Erreur lors de la modification : ${réponse.code}")
            }
        } catch(e: Exception) {
            throw SourceDeDonnéesException("Erreur lors de la modification : ${e.message}")
        }
    }

    override suspend fun ajouterGabarit(gabarit: Gabarits) {
        try {
            val client = OkHttpClient()
            val token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IllUa05idDBNQjhfWm0ydGM4aGlPNyJ9.eyJpc3MiOiJodHRwczovL2Rldi10bXN5bGhjcW15bDYzbHJ5LnVzLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw2NTZlMjJkYTM0NDA4ZTczMWMzYjAxNTMiLCJhdWQiOiJodHRwOi8vZ2FzcGlsbGFnZXplcm8uZGVtbyIsImlhdCI6MTcwMjk2MjYyMSwiZXhwIjoxNzAzMDQ5MDIxLCJhenAiOiJjaXRFaVZDTWZQeW54SjQzOWZ6cGt2a3l4OHlqTE9sZCIsImd0eSI6InBhc3N3b3JkIn0.X7KHgbdc2-tNrayrh7FqaPQSjlG0UCHSE1tkIpkz82QOrzj6HZFl0fEDytoX2_gmloBI_SygUs0FyfyMPHhu6n9nDd_r8pGheClbI62z7xCgOQuw7X9ivat2MEY2UDfCMo4Q1XUr700jaAwv5lI-XSsM-r8unvoxms7TwJ6lS8fZXRriBISwTDGedqBy6KbUmn9cZHKNhIdRdqFfO5eDztIByQAfnxSx6molTolsysX3lKTL2pL7gNMhYEDFiLcRgGcwWTJarVoaspDOYBL5JjjwyAht6TLEjkiT3EELcWjkCuMI2lN0dhsu6wKTQ7vH9qJ1UlxF1IDAQtwolnk1wQ"
            val json = JSONObject().apply {
                put("idGabaritProduit", gabarit.code.toInt())
                put("nom", gabarit.nom)
                put("description", gabarit.description)
                put("image", gabarit.image)
                put("categorie", gabarit.catégorie)
                put("épicerie", JSONObject().apply {
                    put("idÉpicerie", gabarit.épicerie.code.toInt())
                    put("adresse", null)
                    put("utilisateur", null)
                    put("nom", gabarit.épicerie.nom)
                    put("courriel", gabarit.épicerie.courriel)
                    put("téléphone", gabarit.épicerie.téléphone)
                    put("logo", gabarit.épicerie.logo)
                })
            }

            val requestBody = json.toString().toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

            val requête = Request.Builder()
                .url("http://10.0.2.2:8080/gabaritproduit") // URL pour l'ajout de gabarit
                .addHeader("Authorization", "Bearer $token")
                .post(requestBody)
                .build()

            val réponse = client.newCall(requête).execute()

            if (réponse.code != 201) {
                throw SourceDeDonnéesException("Erreur lors de l'ajout : ${réponse.code}")
            }
        } catch(e: Exception) {
            throw SourceDeDonnéesException("Erreur lors de l'ajout : ${e.message}")
        }
    }


    val liste_de_produits = mutableListOf<Produits>()
    val liste_de_magasin = mutableListOf<Magasins>()

    override suspend fun obtenirDonnéesProduits(): List<Produits> {

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

    @Throws(com.example.gaspillezero.ui.main.Exception.SourceDeDonnéesException::class)
    override suspend fun obtenirDonnéesCommandes(): List<Commandes> = withContext(Dispatchers.IO) {
        try {
            val client = OkHttpClient()
            val requête = Request.Builder().url("https://3b53d418-1355-4085-926d-24d685d2da78.mock.pstmn.io/commandes").build()

            val réponse = client.newCall(requête).execute();

            if (!réponse.isSuccessful) {
                throw com.example.gaspillezero.ui.main.Exception.SourceDeDonnéesException("Erreur : ${réponse.code}")
            }

            val body: ResponseBody? = réponse.body
            if (body == null) {
                throw com.example.gaspillezero.ui.main.Exception.SourceDeDonnéesException("Pas de données reçues")
            }

            val jsonDonnées = réponse.body!!.string()
            décoderJsonDonnées(jsonDonnées)
        } catch (e: java.io.IOException) {
            throw com.example.gaspillezero.ui.main.Exception.SourceDeDonnéesException(e.message ?: "Erreur inconnue")
        }
    }

    private fun décoderJsonDonnées(jsonDonnées: String): List<Commandes> {
        return Json.decodeFromString(jsonDonnées)
    }

}