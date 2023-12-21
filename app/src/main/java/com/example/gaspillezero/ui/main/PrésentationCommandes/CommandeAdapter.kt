package com.example.gaspillezero.ui.main.PrésentationCommandes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gaspillezero.R
import com.example.gaspillezero.ui.main.DossierPanier.MyDatabase
import com.example.gaspillezero.ui.main.PrésentationGabarits.GabaritAdapter
import com.example.gaspillezero.ui.main.sourceDeDonnées.Commandes
import com.example.gaspillezero.ui.main.sourceDeDonnées.Gabarits

class CommandeAdapter(
    private val dataSet: MutableList<Commandes>,
    private val onDeleteClick: (Commandes) -> Unit
) : RecyclerView.Adapter<CommandeAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val code: TextView = view.findViewById(R.id.codeCommande)
        val produit: TextView = view.findViewById(R.id.nomProduitC)
        val quantite: TextView = view.findViewById(R.id.quantiteProduitC)
        val deleteButton: Button = view.findViewById(R.id.btnDelete3)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_commande, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val commande = dataSet[position]

        viewHolder.code.text = commande.code.toString()
        viewHolder.produit.text = commande.produit.toString()
        viewHolder.quantite.text = commande.quantite.toString()

        viewHolder.deleteButton.setOnClickListener{
            onDeleteClick(commande)
            removeItem(commande)
        }
    }

    fun setCommandes(commandes: List<Commandes>) {
        dataSet.clear()
        dataSet.addAll(commandes)
        notifyDataSetChanged()
    }

    fun removeItem(commandes: Commandes) {
        val position = dataSet.indexOf(commandes)
        if (position >= 0) {
            dataSet.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount() = dataSet.size

}