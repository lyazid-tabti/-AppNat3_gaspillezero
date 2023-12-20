package com.example.gaspillezero.ui.main.PrésentationCommandes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gaspillezero.R
import com.example.gaspillezero.ui.main.DossierPanier.MyDatabase
import com.example.gaspillezero.ui.main.sourceDeDonnées.Commandes

class CommandeAdapter(private val dataSet: List<Commandes>, private val context: Context, var database: MyDatabase) :
    RecyclerView.Adapter<CommandeAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val code: TextView
        val produit: TextView
        val quantite: TextView

        init {
            code = view.findViewById(R.id.codeCommande)
            produit = view.findViewById(R.id.nomProduitC)
            quantite = view.findViewById(R.id.quantiteProduitC)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.liste_de_commandes, viewGroup, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = dataSet.size


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val commande = dataSet[position]

        viewHolder.code.text = commande.code.toString()
        viewHolder.produit.text = commande.produit
        viewHolder.quantite.text = commande.quantite.toString()
    }
}