package com.example.gaspillezero.ui.main.sourceDeDonnées

import com.example.gaspillezero.ui.main.Exception.SourceDeDonnéesException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import kotlinx.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.IOException

class SourceDonnéesCommandesHTTP(var url_api: String): SourceDonnéesCommandes {

    @Throws(SourceDeDonnéesException::class)
    override suspend fun obtenirDonnéesCommandes(): List<Commandes> = withContext(Dispatchers.IO) {
        try {
            val client = OkHttpClient()
            val requête = Request.Builder().url(url_api).build()

            val réponse = client.newCall(requête).execute();

            if (!réponse.isSuccessful) {
                throw SourceDeDonnéesException("Erreur : ${réponse.code}")
            }

            val body: ResponseBody? = réponse.body
            if (body == null) {
                throw SourceDeDonnéesException("Pas de données reçues")
            }

            val jsonDonnées = réponse.body!!.string()
            décoderJsonDonnées(jsonDonnées)
        } catch (e: IOException) {
            throw SourceDeDonnéesException(e.message ?: "Erreur inconnue")
        }
    }

    private fun décoderJsonDonnées(jsonDonnées: String): List<Commandes> {
        return Json.decodeFromString(jsonDonnées)
    }

}