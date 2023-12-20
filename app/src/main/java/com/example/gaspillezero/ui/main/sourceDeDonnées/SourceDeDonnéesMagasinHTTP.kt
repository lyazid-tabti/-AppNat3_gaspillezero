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

class SourceDeDonnéesMagasinHTTP( ): SourceDonnéesMagasin  {


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


            val token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IllUa05idDBNQjhfWm0ydGM4aGlPNyJ9.eyJpc3MiOiJodHRwczovL2Rldi10bXN5bGhjcW15bDYzbHJ5LnVzLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw2NTZlMjJkYTM0NDA4ZTczMWMzYjAxNTMiLCJhdWQiOiJodHRwOi8vZ2FzcGlsbGFnZXplcm8uZGVtbyIsImlhdCI6MTcwMzEwNDc3NSwiZXhwIjoxNzAzMTkxMTc1LCJhenAiOiJjaXRFaVZDTWZQeW54SjQzOWZ6cGt2a3l4OHlqTE9sZCIsImd0eSI6InBhc3N3b3JkIn0.kE0cfUqeDO8rb8p6iEJRkPwwSxRF_8guJtAbOhALQA7686ihGwS5J3_UHGirKfwWs2E9MzvbxqQ5iJihlWMiYlaEotu6vjlTmFk4GTiDA7TEp6IHL7IbG1MjeQvtiPoB-a26dEawxHnMLWHohpJiaEtqGBjL6lSwFwPpCfWTR9x8mDOIMIIAaHgGR0_CzmOI4xWVHaTAOYTmDEmPptqgD9bL6wExaH_A97KeqMb8dw9wfPkb9EJHKVLNOYEySQXjeAS8kIN8vE134fxMg-yypocmybTNWPFvX-tn88bqC9CnsbMb2one2scy7kk401ihDXNPbPhJWzjnP932IAtdzg"

            val requête = Request.Builder()
                .url("http://10.0.2.2:8080/épiceries")
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