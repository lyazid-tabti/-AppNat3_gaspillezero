package com.example.gaspillezero.ui.main.vue

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gaspillezero.R
import com.example.gaspillezero.ui.main.AppDatabase
import com.example.gaspillezero.ui.main.PanierAdapter
import com.example.gaspillezero.ui.main.sourceDeDonnées.Magasins
import com.example.gaspillezero.ui.main.sourceDeDonnées.PanierItem
import com.squareup.picasso.Picasso

class MagasinAdapter(private var dataSet: List<Magasins>) :
    RecyclerView.Adapter<MagasinAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageMagasin: ImageView
        val nomMagasin: TextView
        val StatutLivraison: TextView



        init {
            imageMagasin = view.findViewById(R.id.imageMagasin)
            nomMagasin = view.findViewById(R.id.nomMagasin)
            StatutLivraison = view.findViewById(R.id.StatutLivraison)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.liste_magasin, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val magasinEpecerie = dataSet[position]

        holder.nomMagasin.text = magasinEpecerie.magasinNom
        val image = holder.imageMagasin.context.resources.getIdentifier(magasinEpecerie.imageID, "drawable", holder.imageMagasin.context.packageName)
        holder.StatutLivraison.text = magasinEpecerie.estDisponible

        Picasso.get()
            .load(image)
            .into(holder.imageMagasin)

    }

    override fun getItemCount()= dataSet.size
}