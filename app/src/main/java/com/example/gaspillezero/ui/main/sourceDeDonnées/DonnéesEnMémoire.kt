package com.example.gaspillezero.ui.main.sourceDeDonnées

class DonnéesEnMémoire : SourceDeDonnées {

    val liste_de_produits = mutableListOf<Produits>()
    val liste_de_magasin = mutableListOf<Magasins>()
    val liste_de_gabarits = mutableListOf<Gabarits>()
    val liste_de_commandes = mutableListOf<Commandes>()
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

    override fun obtenirDonnéesGabarits(): List<Gabarits> {
        if (liste_de_gabarits.isEmpty()) {

            val gabarit1 = Gabarits(
                code = "123",
                nom = "Poulet",
                description = "Le poulet, un oiseau domestique, est largement consommé pour sa chair tendre et riche en protéines. Il est adaptable à divers styles de cuisine.",
                image = "chicken",
                catégorie = "Viande"
            )

            val gabarit2 = Gabarits(
                code = "456",
                nom = "Fromage",
                description = "Le fromage, riche en calcium et protéines, se distingue par sa texture et son goût variés.",
                image = "fromage",
                catégorie = "Laitier"
            )

            val gabarit3 = Gabarits(
                code = "789",
                nom = "Pomme",
                description = "La pomme est un fruit juteux et croquant, généralement rouge, vert ou jaune, produit par le pommier, apprécié pour son goût sucré à acidulé.",
                image = "pomme",
                catégorie = "Fruit"
            )

            val gabarit4 = Gabarits(
                code = "012",
                nom = "Orange",
                description = "L'orange est un agrume juteux, généralement de couleur orange, connu pour son goût à la fois sucré et légèrement acide, riche en vitamine C.",
                image = "orange",
                catégorie = "Fruit"
            )

            liste_de_gabarits.add(gabarit1)
            liste_de_gabarits.add(gabarit2)
            liste_de_gabarits.add(gabarit3)
            liste_de_gabarits.add(gabarit4)

        }

        return liste_de_gabarits
    }

    override fun obtenirDonnéesCommandes(): List<Commandes>{
        val commande1 = Commandes(
            code = 1,
            produitNom = obtenirDonnéesProduits()[0].nom,
            quantité = 4
        )
        val commande2 = Commandes(
            code = 2,
            produitNom = obtenirDonnéesProduits()[1].nom,
            quantité = 3
        )
        val commande3 = Commandes(
            code = 3,
            produitNom = obtenirDonnéesProduits()[2].nom,
            quantité = 5
        )
        val commande4 = Commandes(
            code = 4,
            produitNom = obtenirDonnéesProduits()[1].nom,
            quantité = 7
        )
        val commande5 = Commandes(
            code = 5,
            produitNom = obtenirDonnéesProduits()[0].nom,
            quantité = 5
        )
        liste_de_commandes.add(commande1)
        liste_de_commandes.add(commande2)
        liste_de_commandes.add(commande3)
        liste_de_commandes.add(commande4)
        liste_de_commandes.add(commande5)

        return liste_de_commandes
    }

    override fun supprimerGabarit(gabarit: Gabarits) {
        liste_de_gabarits.remove(gabarit)
    }

}