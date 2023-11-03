package com.example.gaspillezero

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment

class GestionProduit : Fragment() {

    private lateinit var listView: ListView
    private lateinit var customAdapter: CustomAdapter

    //MutableList
    private val dataList = mutableListOf(
        Produit("Fraises", "2024-01-01", 5, 3.99),
        Produit("Saumon", "2024-05-21", 10, 1.99),
        Produit("Spaghetti", "2023-12-31", 8, 2.49)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gestion_produit, container, false)
        listView = view.findViewById(R.id.lvProduits)
        customAdapter = CustomAdapter(requireContext(), dataList)
        listView.adapter = customAdapter
        return view
    }

    private inner class CustomAdapter(private val context: Context, private val dataList: MutableList<Produit>) : BaseAdapter() {
        override fun getCount(): Int = dataList.size
        override fun getItem(position: Int): Any = dataList[position]
        override fun getItemId(position: Int): Long = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.produit_item, parent, false)

            val produit = getItem(position) as Produit
            view.findViewById<TextView>(R.id.tvNomProduit).text = produit.nom
            view.findViewById<TextView>(R.id.tvDateExpiration).text = produit.dateExpiration
            view.findViewById<TextView>(R.id.tvQuantite).text = produit.quantite.toString()
            view.findViewById<TextView>(R.id.tvPrix).text = String.format("%.2f $", produit.prix)

            //gestion du clic sur l'icône de suppression
            view.findViewById<ImageView>(R.id.image_delete).setOnClickListener {
                removeItem(position)
            }

            return view
        }

        //fonction supprimer
        fun removeItem(position: Int) {
            dataList.removeAt(position)
            notifyDataSetChanged() //actualiser la liste
        }
    }


    data class Produit(
        val nom: String,
        val dateExpiration: String,
        val quantite: Int,
        val prix: Double
    )

    companion object {
        @JvmStatic
        fun newInstance() = GestionProduit()
    }
}
