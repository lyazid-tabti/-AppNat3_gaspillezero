package com.example.gaspillezero.ui.main.PrésentationDenrées
import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gaspillezero.R
import com.example.gaspillezero.ui.main.DossierPanier.MyDatabase
import com.example.gaspillezero.ui.main.sourceDeDonnées.PanierItem
import com.example.gaspillezero.ui.main.sourceDeDonnées.Produits
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso
import android.os.Handler
import android.os.Looper
import android.util.Base64
import kotlinx.coroutines.*

class DenréesAdapter(private val dataSet: List<Produits>, private val context: Context, var database: MyDatabase) :
    RecyclerView.Adapter<DenréesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nomProduit: TextView
        val prixProduit: TextView
        val imageProduit: ImageView
        val dateExpProduit: TextView
        val quantitéCommandé: TextInputEditText
        val quantiteStockProduit: TextView
        val ajoutPanier : Button
        val modifierQuantité : Button

        init {
            nomProduit = view.findViewById(R.id.nomProduit)
            prixProduit = view.findViewById(R.id.prixProduit)
            imageProduit = view.findViewById(R.id.imageProduit)
            dateExpProduit = view.findViewById(R.id.dateExpProduit)
            quantiteStockProduit = view.findViewById(R.id.quantiteStockProduit)
            quantitéCommandé = view.findViewById(R.id.quantitéCommandé)
            ajoutPanier = view.findViewById(R.id.btnAjouterPanier)
            modifierQuantité = view.findViewById(R.id.btnModifierPanier)
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

        if (produit.gabarit.image != null && produit.gabarit.image.isNotEmpty()) {
            try {
                val imageBytes = Base64.decode(produit.gabarit.image, Base64.DEFAULT)
                val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                viewHolder.imageProduit.setImageBitmap(bitmap)
            } catch (e: IllegalArgumentException) {
                // Gérer l'exception si la chaîne Base64 n'est pas valide
                viewHolder.imageProduit.setImageResource(R.drawable.pomme)
            }
        } else {
            viewHolder.imageProduit.setImageResource(R.drawable.pomme)
        }

        val ajoutPanier = viewHolder.ajoutPanier
        val modifierQuantité = viewHolder.modifierQuantité
        val handler = Handler(Looper.getMainLooper())

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
                        val item = PanierItem(
                            produitNom = produitNom,
                            produitPrix = produitPrix.toDouble(),
                            quantitéCommandé = quantitéCommandé.toInt(),
                            imageID = produit.gabarit.image.toString()
                        )
                        database.panierDAO().ajouterProduit(item)
                    }
                    handler.post {
                        ajoutPanier.isEnabled = false
                        modifierQuantité.visibility = View.VISIBLE
                    }
                }
            } else {
                Toast.makeText(context, "Erreur, quantité non valide!", Toast.LENGTH_SHORT).show()
            }
        }

        modifierQuantité.setOnClickListener {

                val quantitéCommandé = viewHolder.quantitéCommandé.text.toString()
                val produitNom = viewHolder.nomProduit.text.toString()
                CoroutineScope(Dispatchers.IO).launch {
                    val produit_existant =
                        database.panierDAO().chercherProduitParNom(produitNom)

                    if (produit_existant != null) {

                        database.panierDAO().modifierPanierItem(quantitéCommandé, produitNom)
                    }
                }
            }
        }

    override fun getItemCount() = dataSet.size
}
