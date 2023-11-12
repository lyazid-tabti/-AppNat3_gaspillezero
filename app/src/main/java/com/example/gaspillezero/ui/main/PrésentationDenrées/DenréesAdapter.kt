package com.example.gaspillezero.ui.main.PrésentationDenrées
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gaspillezero.R
import com.example.gaspillezero.ui.main.sourceDeDonnées.Produits
import com.squareup.picasso.Picasso

class DenréesAdapter(private val dataSet: List<Produits>) :
    RecyclerView.Adapter<DenréesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nomProduit: TextView
        val prixProduit: TextView
        val imageProduit: ImageView
        val dateExpProduit: TextView
        val quantiteStockProduit: TextView

        init {
            nomProduit = view.findViewById(R.id.nomProduit)
            prixProduit = view.findViewById(R.id.prixProduit)
            imageProduit = view.findViewById(R.id.imageProduit)
            dateExpProduit = view.findViewById(R.id.dateExpProduit)
            quantiteStockProduit = view.findViewById(R.id.quantiteStockProduit)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.liste_de_produits, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val produit = dataSet[position]

        viewHolder.nomProduit.text = produit.nom
        viewHolder.prixProduit.text = produit.prix.toString() + "$"
        viewHolder.dateExpProduit.text = "Date d'exp.: " + produit.date_exp
        viewHolder.quantiteStockProduit.text = "Quantité en stock: " + produit.quantite_stock.toString()
        val image = viewHolder.imageProduit.context.resources.getIdentifier(produit.photo_url, "drawable", viewHolder.imageProduit.context.packageName)

        Picasso.get()
            .load(image)
            .into(viewHolder.imageProduit)
    }

    override fun getItemCount() = dataSet.size

}
