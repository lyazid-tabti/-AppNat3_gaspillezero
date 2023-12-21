package com.example.gaspillezero.ui.main.DossierPanier

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
import com.example.gaspillezero.ui.main.sourceDeDonnées.PanierItem
import com.squareup.picasso.Picasso

class PanierAdapter(private var dataSet: MutableList<PanierItem>,  var database: MyDatabase) :
    RecyclerView.Adapter<PanierAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val produitImage: ImageView
        val produitNom: TextView
        val produitQuantiteDesire: TextView
        val produitPrix: TextView
        val supprimerBtnPanier: Button

        init {
            produitImage = view.findViewById(R.id.produitImage)
            produitNom = view.findViewById(R.id.produitNom)
            produitQuantiteDesire = view.findViewById(R.id.produitQuantite)
            produitPrix = view.findViewById(R.id.produitPrix)
            supprimerBtnPanier = view.findViewById(R.id.btnSupprimer)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.panier_produits, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val panierItem = dataSet[position]

        val totalPrix = panierItem.produitPrix * panierItem.quantitéCommandé
        viewHolder.produitNom.text = "Nom: " + panierItem.produitNom
        viewHolder.produitQuantiteDesire.text = "Quantité: " + panierItem.quantitéCommandé
        viewHolder.produitPrix.text = "Prix totale: " + "%.2f".format(totalPrix) + "$"

        if (panierItem.imageID != null && panierItem.imageID.isNotEmpty()) {
            try {
                val imageBytes = Base64.decode(panierItem.imageID, Base64.DEFAULT)
                val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                viewHolder.produitImage.setImageBitmap(bitmap)
            } catch (e: IllegalArgumentException) {
                // Gérer l'exception si la chaîne Base64 n'est pas valide
                viewHolder.produitImage.setImageResource(R.drawable.pomme)
            }
        } else {
            viewHolder.produitImage.setImageResource(R.drawable.pomme)
        }

        val supprimerBtn = viewHolder.supprimerBtnPanier

        supprimerBtn.setOnClickListener {

            database.panierDAO().supprimerPanierItem(panierItem)
            dataSet.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, dataSet.size)
        }
    }

    override fun getItemCount() = dataSet.size
}
