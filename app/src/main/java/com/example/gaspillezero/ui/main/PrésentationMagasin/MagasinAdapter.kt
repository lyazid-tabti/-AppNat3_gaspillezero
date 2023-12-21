package com.example.gaspillezero.ui.main.PrésentationMagasin

import android.graphics.BitmapFactory
import android.util.Base64
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
import com.example.gaspillezero.ui.main.sourceDeDonnées.Épicerie
import com.squareup.picasso.Picasso

class MagasinAdapter(private var dataSet: List<Épicerie>) :
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

        /*if (magasinEpecerie.logo != null && magasinEpecerie.logo.isNotEmpty()) {
            try {
                val imageBytes = Base64.decode(magasinEpecerie.logo, Base64.DEFAULT)
                val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                holder.imageMagasin.setImageBitmap(bitmap)
            } catch (e: IllegalArgumentException) {
                // Gérer l'exception si la chaîne Base64 n'est pas valide
                holder.imageMagasin.setImageResource(R.drawable.pomme)
            }
        } else {
            val lol =holder.imageMagasin.context.resources.getIdentifier(magasinEpecerie.logo, "drawable", holder.imageMagasin.context.packageName)
            Picasso.get()
                .load(lol)
                .into(holder.imageMagasin)
        }*/
        holder.nomMagasin.text = magasinEpecerie.nom
        //val image = holder.imageMagasin.context.resources.getIdentifier(magasinEpecerie.logo, "drawable", holder.imageMagasin.context.packageName)
        holder.StatutLivraison.text = magasinEpecerie.téléphone
        val btnCommander = holder.btnCommander

        if (magasinEpecerie.logo != null && magasinEpecerie.logo.isNotEmpty()) {
            try {
                val imageBytes = Base64.decode(magasinEpecerie.logo, Base64.DEFAULT)
                val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                holder.imageMagasin.setImageBitmap(bitmap)
            } catch (e: IllegalArgumentException) {
                // Gérer l'exception si la chaîne Base64 n'est pas valide
                holder.imageMagasin.setImageResource(R.drawable.logo_base)
            }
        } else {
            holder.imageMagasin.setImageResource(R.drawable.logo_base)
        }

        btnCommander.setOnClickListener {
            holder.itemView.findNavController().navigate(R.id.action_fragment_epecerie_to_denreesFragment)
        }
    }

    override fun getItemCount()= dataSet.size
}