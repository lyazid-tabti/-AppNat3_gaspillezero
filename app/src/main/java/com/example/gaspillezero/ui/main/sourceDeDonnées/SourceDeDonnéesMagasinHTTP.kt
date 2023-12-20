package com.example.gaspillezero.ui.main.sourceDeDonnées

import android.util.JsonWriter
import com.example.gaspillezero.ui.main.Exception.SourceDeDonnéesException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.ResponseBody
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.OutputStreamWriter

class SourceDeDonnéesMagasinHTTP(var url_api : String ): SourceDonnéesMagasin  {


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
                throw com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDeDonnéesException("Code :" + réponse.code)
            }
        }
        catch(e: IOException){
            throw com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDeDonnéesException(
                e.message ?: "Erreur inconnue"
            )
        }
    }




    @Throws(com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDeDonnéesException::class)
    override suspend fun obtenirListeÉpecerie(): List<Épicerie> {
        try {
            val client = OkHttpClient()


            val token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IllUa05idDBNQjhfWm0ydGM4aGlPNyJ9.eyJpc3MiOiJodHRwczovL2Rldi10bXN5bGhjcW15bDYzbHJ5LnVzLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw2NTZlMjJkYTM0NDA4ZTczMWMzYjAxNTMiLCJhdWQiOiJodHRwOi8vZ2FzcGlsbGFnZXplcm8uZGVtbyIsImlhdCI6MTcwMjk2MjYyMSwiZXhwIjoxNzAzMDQ5MDIxLCJhenAiOiJjaXRFaVZDTWZQeW54SjQzOWZ6cGt2a3l4OHlqTE9sZCIsImd0eSI6InBhc3N3b3JkIn0.X7KHgbdc2-tNrayrh7FqaPQSjlG0UCHSE1tkIpkz82QOrzj6HZFl0fEDytoX2_gmloBI_SygUs0FyfyMPHhu6n9nDd_r8pGheClbI62z7xCgOQuw7X9ivat2MEY2UDfCMo4Q1XUr700jaAwv5lI-XSsM-r8unvoxms7TwJ6lS8fZXRriBISwTDGedqBy6KbUmn9cZHKNhIdRdqFfO5eDztIByQAfnxSx6molTolsysX3lKTL2pL7gNMhYEDFiLcRgGcwWTJarVoaspDOYBL5JjjwyAht6TLEjkiT3EELcWjkCuMI2lN0dhsu6wKTQ7vH9qJ1UlxF1IDAQtwolnk1wQ"

            val requête = Request.Builder()
                .url("http://10.0.2.2:8080/épeceries")
                .addHeader("Authorization", "Bearer $token") // Ajout de l'en-tête d'authentification
                .build()

            val réponse = client.newCall(requête).execute()

            if (réponse.code != 200) {
                throw com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDeDonnéesException("Erreur : ${réponse.code}")
            }
            if (réponse.body == null) {
                throw com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDeDonnéesException("Pas de données reçues")
            }

            // Utilisation du décodeur JSON pour convertir la réponse en liste de Gabarits
            return DécodeurJson.décoderJsonVersListeÉpeceries(réponse.body!!.string())
        } catch(e: IOException) {
            throw com.example.gaspillezero.ui.main.sourceDeDonnées.SourceDeDonnéesException(
                e.message ?: "Erreur inconnue"
            )
        }
    }


}