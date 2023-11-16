package com.example.gaspillezero.ui.main.sourceDeDonnées

class DonnéesEnMémoire : SourceDeDonnées {

    val liste_de_produits = mutableListOf<Produits>()

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

        liste_de_produits.add(produit1)
        liste_de_produits.add(produit2)

        return liste_de_produits
    }
}