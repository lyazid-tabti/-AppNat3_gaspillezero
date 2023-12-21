package com.example.gaspillezero.ui.main.PrésentationProduits

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
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
import com.example.gaspillezero.ui.main.PrésentationGabarits.GabaritAdapter
import com.example.gaspillezero.ui.main.sourceDeDonnées.Gabarits
import com.example.gaspillezero.ui.main.sourceDeDonnées.Produits
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class ProduitAdapter(
    private val dataSet: MutableList<Produits>,
    private val onDeleteClick: (Produits) -> Unit,
    private val onEditClick: (Produits) -> Unit
) : RecyclerView.Adapter<ProduitAdapter.ViewHolder>() {

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val nomProduit: TextView = view.findViewById(R.id.nomProduitG)
        val prixProduit: TextView= view.findViewById(R.id.prixProduitG)
        val imageProduit: ImageView = view.findViewById(R.id.imageProduitG)
        val dateExpProduit: TextView = view.findViewById(R.id.dateExpProduitG)
        val quantiteStockProduit: TextView = view.findViewById(R.id.quantiteStockProduitG)
        val descriptionProduit: TextView = view.findViewById(R.id.descriptionProduitG)
        val deleteButton: Button = view.findViewById(R.id.btnDelete2)
        val editButton: Button = view.findViewById(R.id.btnEdit2)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.liste_de_produits_gestion, viewGroup, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val produit = dataSet[position]

        viewHolder.nomProduit.text = produit.nom
        viewHolder.prixProduit.text = produit.prix.toString() + "$"
        viewHolder.dateExpProduit.text = "Date d'exp.: " + produit.date_exp
        viewHolder.quantiteStockProduit.text = "Quantité en stock: " + produit.quantite_stock.toString()
        viewHolder.descriptionProduit.text = "Description: " + produit.gabarit.description

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


        viewHolder.deleteButton.setOnClickListener {
            onDeleteClick(produit)
            removeItem(produit)
        }
        viewHolder.editButton.setOnClickListener {
            onEditClick(produit)
        }
    }

    override fun getItemCount() = dataSet.size

    fun setProduits(produits: List<Produits>) {
        dataSet.clear()
        dataSet.addAll(produits)
        notifyDataSetChanged()
    }

    fun removeItem(produits: Produits){
        val position = dataSet.indexOf(produits)
        if (position >= 0) {
            dataSet.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun modifierProduits(produits: Produits, nouveauProduits: Produits) {
        val position = dataSet.indexOf(produits)
        if (position >= 0) {
            dataSet[position] = nouveauProduits
            notifyItemChanged(position)
        }
    }

    fun ajouterProduit(produit: Produits) {
        dataSet.add(produit)
        notifyItemInserted(dataSet.size - 1)
    }
}