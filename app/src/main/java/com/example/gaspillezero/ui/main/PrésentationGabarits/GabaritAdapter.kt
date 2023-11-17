package com.example.gaspillezero.ui.main.PrésentationGabarits

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gaspillezero.R
import com.example.gaspillezero.ui.main.sourceDeDonnées.Gabarits
import com.squareup.picasso.Picasso

class GabaritAdapter(private val dataSet: List<Gabarits>) :
    RecyclerView.Adapter<GabaritAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nomGabarit: TextView = view.findViewById(R.id.nomGabarit)
        val codeGabarit: TextView = view.findViewById(R.id.codeGabarit)
        val descriptionGabarit: TextView = view.findViewById(R.id.descriptionGabarit)
        val categorieGabarit: TextView = view.findViewById(R.id.categorieGabarit)
        val imageGabarit: ImageView = view.findViewById(R.id.imageGabarit)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_gabarit, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val gabarit = dataSet[position]

        viewHolder.nomGabarit.text = gabarit.nom
        viewHolder.codeGabarit.text = gabarit.code
        viewHolder.descriptionGabarit.text = gabarit.description
        viewHolder.categorieGabarit.text = gabarit.catégorie
        val imageUri = viewHolder.imageGabarit.context.resources.getIdentifier(gabarit.image, "drawable", viewHolder.imageGabarit.context.packageName)

        Picasso.get()
            .load(imageUri)
            .into(viewHolder.imageGabarit)
    }

    override fun getItemCount() = dataSet.size

}