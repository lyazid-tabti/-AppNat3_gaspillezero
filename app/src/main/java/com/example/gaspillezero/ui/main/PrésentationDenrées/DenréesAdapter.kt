package com.example.gaspillezero.ui.main.PrésentationDenrées
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gaspillezero.R
import com.example.gaspillezero.ui.main.AppDatabase
import com.example.gaspillezero.ui.main.sourceDeDonnées.PanierItem
import com.example.gaspillezero.ui.main.sourceDeDonnées.Produits
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DenréesAdapter(private val dataSet: List<Produits>, private val context: Context, var database: AppDatabase) :
    RecyclerView.Adapter<DenréesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nomProduit: TextView
        val prixProduit: TextView
        val imageProduit: ImageView
        val dateExpProduit: TextView
        val quantitéCommandé: TextInputEditText
        val quantiteStockProduit: TextView
        val ajoutPanier : Button

        init {
            nomProduit = view.findViewById(R.id.nomMagasin)
            prixProduit = view.findViewById(R.id.prixProduit)
            imageProduit = view.findViewById(R.id.imageMagasin)
            dateExpProduit = view.findViewById(R.id.dateExpProduit)
            quantiteStockProduit = view.findViewById(R.id.quantiteStockProduit)
            quantitéCommandé = view.findViewById(R.id.quantitéCommandé)
            ajoutPanier = view.findViewById(R.id.btnAjouterPanier)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.liste_de_produits, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val produit = dataSet[position]

        viewHolder.nomProduit.text = produit.nom
        viewHolder.prixProduit.text = produit.prix.toString() + "$"
        viewHolder.dateExpProduit.text = "Date d'exp.: " + produit.date_exp
        viewHolder.quantiteStockProduit.text = "Quantité en stock: " + produit.quantite_stock.toString()
        val image = viewHolder.imageProduit.context.resources.getIdentifier(produit.photo_url, "drawable", viewHolder.imageProduit.context.packageName)
        val ajoutPanier = viewHolder.ajoutPanier

        Picasso.get()
            .load(image)
            .into(viewHolder.imageProduit)

        ajoutPanier.setOnClickListener {

                val quantitéCommandé = viewHolder.quantitéCommandé.text.toString()
                val produitNom = viewHolder.nomProduit.text.toString()
                var produitPrix = viewHolder.prixProduit.text.toString()
                produitPrix = produitPrix.replace("$", "")
            if (quantitéCommandé.isNotEmpty() && quantitéCommandé.toInt() > 0 && quantitéCommandé.toInt() < produit.quantite_stock) {

                    CoroutineScope(Dispatchers.IO).launch {
                        val produit_existant =
                            database.panierDAO().chercherProduitParNom(produitNom)

                        if (produit_existant == null) {
                            val produit = PanierItem(
                                produitNom = produitNom,
                                produitPrix = produitPrix.toDouble(),
                                quantitéCommandé = quantitéCommandé.toInt(),
                                imageID = image.toString()
                            )
                            database.panierDAO().ajouterProduit(produit)
                        } else withContext(Dispatchers.Main) {
                            Toast.makeText(context, "Produit déjà ajouté!", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                } else {
                    Toast.makeText(context, "Erreur, quantité non valide!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    override fun getItemCount() = dataSet.size
}
