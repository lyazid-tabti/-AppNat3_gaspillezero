package com.example.gaspillezero.ui.main.vue

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

class MagasinAdapter(private var dataSet: MutableList<Magasins>,  var database: AppDatabase) :
    RecyclerView.Adapter<MagasinAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageMagasin: ImageView
        val nomMagasin: TextView
        val StatutLivraison: TextView

        val supprimerBtnPanier: Button

        init {
            imageMagasin = view.findViewById(R.id.imageMagasin)
            nomMagasin = view.findViewById(R.id.nomMagasin)
            StatutLivraison = view.findViewById(R.id.StatutLivraison)

            supprimerBtnPanier = view.findViewById(R.id.btnSupprimer)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_epecerie, parent, false)

        return MagasinAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val magasinEpecerie = dataSet[position]

        holder.nomMagasin.text = magasinEpecerie.magasinNom
        //val image = holder.imageMagasin.context.resources.getIdentifier(magasinEpecerie.imageID, "drawable", holder.imageMagasin.context.packageName)
        //holder.nomMagasin.text = magasinEpecerie.estDisponibl

    }

    override fun getItemCount()= dataSet.size
}