package com.example.gaspillezero

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class GestionProduit : Fragment() {

    private lateinit var listView: ListView
    private lateinit var customAdapter: CustomAdapter

    // Données fictives
    private val dataList = listOf(
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
        val ButtonAccueil = view.findViewById<ImageButton>(R.id.Accueil)
        ButtonAccueil.setOnClickListener{
            findNavController().navigate(R.id.action_gestionProduit_to_epicerie_accueil)
        }
        return view
    }

    // Adapter pour le ListView
    private class CustomAdapter(private val context: Context, private val dataList: List<Produit>) : BaseAdapter() {
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

            return view
        }
    }

    // Données de produit
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
