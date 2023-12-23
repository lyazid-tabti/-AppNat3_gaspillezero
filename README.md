**Description de la problématique :**

En tant que compagnie de lutte contre le gaspillage alimentaire, nous avons besoin de développer une application permettant aux clients de consulter la liste des aliments proches de leur date d'expiration mis en vente par les épiceries. Ceci dans le but de réduire le gaspillage alimentaire et d'aider les personnes nécessitant un accès facilité à des aliments abordables.

**Description du projet de développement :**

**Sous-équipe 1 : Audric et Samuel**
Ils sont responsables du développement de l'application dédiée aux Gérants d'Épicerie.
Les Gérants peuvent ajouter, supprimer ou mettre à jour des gabarits de produits.
Les Gérants peuvent également ajouter, supprimer ou mettre à jour des produits en spécifiant le gabarit choisi.
Les Gérants peuvent recevoir des commandes et les finaliser (supprimer).
Ils sont aussi responsables de l'interaction avec la galerie du système Android pour l'ajout et la modification d'images de gabarits de produits.

**Sous-équipe 2 : Lyazid et Gabriel**
Ils sont responsables du développement de l'application pour les Clients.
On a principalement ajouter la fonctionnalité qu'un utilisateur peut ajouter, modifier et supprimer un produit du panier. Nous avons également ajouter une liste déroulante pour le menu afin que l'utilisateur puisse naviguer efficacement dans l'application. Sans oublier que les données reçus dans le fragment 'denrées' et 'épicerie' proviennent de l'API que nous avons tous développés en service d'échange.Pour la sauvegarde, on a utilisé la base de données 'Room' ce qui permet de garder les données stockés localement et il reste sauvegarder peu importe où on se trouve dans l'application. On s'est occupé des fragments 'denrées' et 'épiceries' que l'on a par ailleurs ajouter des recycler view afin d'optimiser les affichages.

**Vérifications importantes :**

La base de données est créée.
Les données sont insérées.
L'API est en exécution sur le même appareil.
L'URL de base dans la source HTTP est correcte.
Le fichier application.properties contient les informations adéquates.
Le Bearer Token dans la source HTTP ne doit pas être expiré.
Le Bearer Token dans la source HTTP appartient à l'utilisateur 2 (il a le rôle "épicerie" et est gérant de l'épicerie 1).

**Détails de la robustesse :**

Notre application ne fonctionne pas en mode Avion.
L'émulateur n'arrive pas à établir de connexion avec l'API sur le réseau de l'école.
Les requêtes de l'API ne doivent pas contenir un grand nombre d'images encodées en Base64, afin d'éviter d'associer trop d'images aux objets. Cela rallonge excessivement le temps de réponse et entraîne des problèmes de robustesse (crash et inutilisabilité de l'application jusqu'à ce que l'API ait fini de traiter la requête). Nous n'étions pas conscients que cela pouvait causer des problèmes lors du développement de l'API.
