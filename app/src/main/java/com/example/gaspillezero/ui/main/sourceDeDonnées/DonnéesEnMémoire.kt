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

    override fun obtenirDonnéesGabarits(): List<Gabarits> {
        val liste_de_gabarits = mutableListOf<Gabarits>()

        val gabarit1 = Gabarits(
            code = "123",
            nom = "Poulet",
            description = "Le poulet, un oiseau domestique, est largement consommé pour sa chair tendre et riche en protéines. Il est adaptable à divers styles de cuisine.",
            image = "chicken",
            catégorie = "viande"
        )

        val gabarit2 = Gabarits(
            code = "456",
            nom = "Fromage",
            description = "Le fromage, riche en calcium et protéines, se distingue par sa texture et son goût variés.",
            image = "fromage",
            catégorie = "laitier"
        )

        liste_de_gabarits.add(gabarit1)
        liste_de_gabarits.add(gabarit2)

        return liste_de_gabarits
    }

}