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

class PanierAdapter(private var dataSet: List<PanierItem>) :
    RecyclerView.Adapter<PanierAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val produitImage: ImageView
        val produitNom: TextView
        val produitQuantiteDesire: TextView
        val produitPrix: TextView
        val removeButton: Button

        init {
            produitImage = view.findViewById(R.id.produitImage)
            produitNom = view.findViewById(R.id.produitNom)
            produitQuantiteDesire = view.findViewById(R.id.produitQuantite)
            produitPrix = view.findViewById(R.id.produitPrix)
            removeButton = view.findViewById(R.id.remove_button)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.panier_produits, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val panierItem = dataSet[position]

        viewHolder.produitNom.text = "Nom: " + panierItem.produitNom
        viewHolder.produitQuantiteDesire.text = "Quantité: " + panierItem.quantitéCommandé
        viewHolder.produitPrix.text = "Prix: " + panierItem.produitPrix
        val image = viewHolder.produitImage.context.resources.getIdentifier(panierItem.imageUrl, "drawable", viewHolder.produitImage.context.packageName)

        Picasso.get()
            .load(image)
            .into(viewHolder.produitImage)
    }

    override fun getItemCount() = dataSet.size
}
