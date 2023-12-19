package com.example.gaspillezero.ui.main.PrésentationMagasin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.gaspillezero.R
import com.example.gaspillezero.ui.main.PrésentationDenrées.DenreesFragment
import com.example.gaspillezero.ui.main.sourceDeDonnées.Magasins
import com.squareup.picasso.Picasso

class MagasinAdapter(private var dataSet: List<Magasins>) :
    RecyclerView.Adapter<MagasinAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageMagasin: ImageView
        val nomMagasin: TextView
        val StatutLivraison: TextView
        val btnCommander: Button


        init {
            imageMagasin = view.findViewById(R.id.imageMagasin)
            nomMagasin = view.findViewById(R.id.nomMagasin)
            StatutLivraison = view.findViewById(R.id.StatutLivraison)
            btnCommander = view.findViewById(R.id.btnarticle)
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
        val btnCommander = holder.btnCommander

        Picasso.get()
            .load(image)
            .into(holder.imageMagasin)

        btnCommander.setOnClickListener {
            holder.itemView.findNavController().navigate(R.id.action_fragment_epecerie_to_denreesFragment)
        }
    }

    override fun getItemCount()= dataSet.size
}