package com.example.gaspillezero.ui.main

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

class PanierAdapter(private var dataSet: MutableList<PanierItem>,  var database: AppDatabase) :
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
        val image = viewHolder.produitImage.context.resources.getIdentifier(panierItem.imageID, "drawable", viewHolder.produitImage.context.packageName)
        val supprimerBtn = viewHolder.supprimerBtnPanier

        Picasso.get()
            .load(image)
            .into(viewHolder.produitImage)

        supprimerBtn.setOnClickListener {

            database.panierDAO().supprimerPanierItem(panierItem)
            dataSet.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, dataSet.size)
        }
    }

    override fun getItemCount() = dataSet.size
}
