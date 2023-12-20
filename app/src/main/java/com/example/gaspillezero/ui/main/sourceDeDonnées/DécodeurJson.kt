package com.example.gaspillezero.ui.main.sourceDeDonnées

import android.util.JsonReader
import android.util.JsonToken
import java.io.StringReader
import java.sql.Blob

class DécodeurJson {
    companion object {

        fun décoderJsonVersGabarits(reader: JsonReader): Gabarits {
            var code: Int = 0
            var nom: String? = null
            var description: String? = null
            var image: String? = null
            var catégorie: String? = null
            lateinit var épicerie: Épicerie

            reader.beginObject()
            while (reader.hasNext()) {
                val clé = reader.nextName()
                when (clé) {
                    "idGabaritProduit" -> { code = reader.nextInt() }
                    "nom" -> { nom = if (reader.peek() != JsonToken.NULL) reader.nextString() else { reader.nextNull(); null } }
                    "description" -> { description = if (reader.peek() != JsonToken.NULL) reader.nextString() else { reader.nextNull(); null } }
                    "image" -> { image = if (reader.peek() != JsonToken.NULL) reader.nextString() else { reader.nextNull(); "pomme" } }
                    "categorie" -> { catégorie = if (reader.peek() != JsonToken.NULL) reader.nextString() else { reader.nextNull(); null } }
                    "épicerie" -> {
                        épicerie = décoderJsonVersÉpicerie(reader)
                    }
                }
            }
            reader.endObject()

            // You need to handle nullability appropriately where Gabarits is initialized
            return Gabarits(code.toString(), nom!!, description!!, image, catégorie!!, épicerie)
        }


        fun décoderJsonVersListeGabarits(json: String): List<Gabarits> {
            val gabaritsListe = mutableListOf<Gabarits>()
            val reader = JsonReader(StringReader(json))

            reader.beginArray()
            while (reader.hasNext()) {
                gabaritsListe.add(décoderJsonVersGabarits(reader))
            }
            reader.endArray()

            return gabaritsListe
        }

        private fun décoderJsonVersÉpicerie( reader: JsonReader ): Épicerie{
            //lateinit
            var code : Int = 0
            var adresse : Adresse? = null
            var utilisateur : Utilisateur? = null
            var nom : String? = null
            var courriel : String? = null
            var téléphone : String? = null
            var logo : String? = null

            reader.beginObject()
            while (reader.hasNext() ) {
                var clé = reader.nextName()
                when( clé ) {
                    "idÉpicerie" -> { code = reader.nextInt() }
                    "adresse" -> { adresse = décoderJsonVersAdresse( reader ) }
                    "utilisateur" -> { utilisateur = décoderJsonVersUtilisateur( reader ) }
                    "nom" -> { nom = if (reader.peek() != JsonToken.NULL) reader.nextString() else { reader.nextNull(); null } }
                    "courriel" -> { courriel = if (reader.peek() != JsonToken.NULL) reader.nextString() else { reader.nextNull(); null } }
                    "téléphone" -> { téléphone = if (reader.peek() != JsonToken.NULL) reader.nextString() else { reader.nextNull(); null } }
                    "logo" -> { logo = if (reader.peek() != JsonToken.NULL) reader.nextString() else { reader.nextNull(); null } }
                }
            }
            reader.endObject()
            return Épicerie( code.toString(), adresse, utilisateur, nom!!, courriel!!, téléphone!!, logo )
        }

        fun décoderJsonVersListeÉpeceries(json: String): List<Épicerie> {
            val épecerieListe = mutableListOf<Épicerie>()
            val reader = JsonReader(StringReader(json))

            reader.beginArray()
            while (reader.hasNext()) {
                épecerieListe.add(décoderJsonVersÉpicerie(reader))
            }
            reader.endArray()

            return épecerieListe
        }

        private fun décoderJsonVersUtilisateur( reader: JsonReader ): Utilisateur{
            //lateinit
            var code : Int = 0
            var nom : String? = null
            var prénom : String? = null
            var courriel : String? = null
            var adresse : Adresse? = null
            var téléphone : String? = null
            var rôle : String? = null

            reader.beginObject()
            while (reader.hasNext() ) {
                var clé = reader.nextName()
                when( clé ) {
                    "code" -> { code = reader.nextInt() }
                    "nom" -> { nom = if (reader.peek() != JsonToken.NULL) reader.nextString() else { reader.nextNull(); null } }
                    "prénom" -> { prénom = if (reader.peek() != JsonToken.NULL) reader.nextString() else { reader.nextNull(); null } }
                    "courriel" -> { courriel = if (reader.peek() != JsonToken.NULL) reader.nextString() else { reader.nextNull(); null } }
                    "adresse" -> { adresse = décoderJsonVersAdresse( reader ) }
                    "téléphone" -> { téléphone = if (reader.peek() != JsonToken.NULL) reader.nextString() else { reader.nextNull(); null } }
                    "rôle" -> { rôle = if (reader.peek() != JsonToken.NULL) reader.nextString() else { reader.nextNull(); null } }
                    "tokenAuth0" -> reader.skipValue() // Ignorer pour l'instant. Je ne penses pas que nous avons besoin pour l'instant
                }
            }
            reader.endObject()
            return Utilisateur( code.toString(), nom!!, prénom!!, courriel!!, adresse, téléphone!!, rôle!! )
        }

        private fun décoderJsonVersAdresse( reader: JsonReader ): Adresse{
            //lateinit
            var code : Int = 0
            var numéro_municipal : String? = null
            var rue : String? = null
            var ville : String? = null
            var province : String? = null
            var code_postal : String? = null
            var pays : String? = null

            reader.beginObject()
            while (reader.hasNext() ) {
                var clé = reader.nextName()
                when( clé ) {
                    "idAdresse" -> { code = reader.nextInt() }
                    "numéro_municipal" -> { numéro_municipal = if (reader.peek() != JsonToken.NULL) reader.nextString() else { reader.nextNull(); null } }
                    "rue" -> { rue = if (reader.peek() != JsonToken.NULL) reader.nextString() else { reader.nextNull(); null } }
                    "ville" -> { ville = if (reader.peek() != JsonToken.NULL) reader.nextString() else { reader.nextNull(); null } }
                    "province" -> { province = if (reader.peek() != JsonToken.NULL) reader.nextString() else { reader.nextNull(); null } }
                    "code_postal" -> { code_postal = if (reader.peek() != JsonToken.NULL) reader.nextString() else { reader.nextNull(); null } }
                    "pays" -> { pays = if (reader.peek() != JsonToken.NULL) reader.nextString() else { reader.nextNull(); null } }
                }
            }
            reader.endObject()
            return Adresse( code.toString(), numéro_municipal!!, rue!!, ville!!, province!!, code_postal!!, pays!!)
        }
    }

}