package com.example.gaspillezero.ui.main.PrésentationProduits

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gaspillezero.R
import com.example.gaspillezero.ui.main.DossierPanier.MyDatabase
import com.example.gaspillezero.ui.main.PrésentationDenrées.DenréesAdapter
import com.example.gaspillezero.ui.main.sourceDeDonnées.Produits
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso

class ProduitAdapter(private val dataSet: List<Produits>, private val context: Context, var database: MyDatabase) :
    RecyclerView.Adapter<ProduitAdapter.ViewHolder>() {

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val nomProduit: TextView
        val prixProduit: TextView
        val imageProduit: ImageView
        val dateExpProduit: TextView
        val quantiteStockProduit: TextView
        val descriptionProduit: TextView

        init {
            nomProduit = view.findViewById(R.id.nomProduitG)
            prixProduit = view.findViewById(R.id.prixProduitG)
            imageProduit = view.findViewById(R.id.imageProduitG)
            dateExpProduit = view.findViewById(R.id.dateExpProduitG)
            quantiteStockProduit = view.findViewById(R.id.quantiteStockProduitG)
            descriptionProduit = view.findViewById(R.id.descriptionProduitG)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.liste_de_produits_gestion, viewGroup, false)

        return ViewHolder(view)
    }


    override fun getItemCount() = dataSet.size


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val produit = dataSet[position]

        viewHolder.nomProduit.text = produit.nom
        viewHolder.prixProduit.text = produit.prix.toString() + "$"
        viewHolder.dateExpProduit.text = "Date d'exp.: " + produit.date_exp
        viewHolder.quantiteStockProduit.text = "Quantité en stock: " + produit.quantite_stock.toString()
        viewHolder.descriptionProduit.text = "Description: " + produit.description
        val image = viewHolder.imageProduit.context.resources.getIdentifier(produit.photo_url, "drawable", viewHolder.imageProduit.context.packageName)

        Picasso.get()
            .load(image)
            .into(viewHolder.imageProduit)
    }


}