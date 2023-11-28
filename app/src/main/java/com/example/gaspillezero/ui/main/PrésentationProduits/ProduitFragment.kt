package com.example.gaspillezero.ui.main.PrésentationProduits

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gaspillezero.R
import com.example.gaspillezero.ui.main.DossierPanier.AppDatabase
import com.example.gaspillezero.ui.main.PrésentationDenrées.DenréesAdapter
import com.example.gaspillezero.ui.main.sourceDeDonnées.Produits

class ProduitFragment : Fragment(), AdapterView.OnItemSelectedListener {

    //private lateinit var listView: ListView
    //private lateinit var customAdapter: CustomAdapter
    var présentateur = ProduitPrésentateur(this)
    private lateinit var adapter: ProduitAdapter
    private lateinit var database: AppDatabase

    companion object {
        fun newInstance() = ProduitFragment()
    }

    /* Données fictives
    private val dataList = listOf(
        Produit("Fraises", "2024-01-01", 5, 3.99),
        Produit("Saumon", "2024-05-21", 10, 1.99),
        Produit("Spaghetti", "2023-12-31", 8, 2.49)
    )*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gestion_produit, container, false)
        //listView = view.findViewById(R.id.lvProduits)
        //customAdapter = CustomAdapter(requireContext(), dataList)
        //listView.adapter = customAdapter
        //val ButtonAccueil = view.findViewById<ImageButton>(R.id.Accueil)
        //ButtonAccueil.setOnClickListener{
        //    findNavController().navigate(R.id.action_gestionProduit_to_epicerie_accueil)
        //}
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinner = view.findViewById<Spinner>(R.id.spinner2)
        spinner.onItemSelectedListener = this
        présentateur.obtenirDonnées()

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_gestionProduit_to_epicerie_accueil)
        }
    }

    // Adapter pour le ListView
    /*
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
    }*/

    // Données de produit
//    data class Produit(
//        val nom: String,
//        val dateExpiration: String,
//        val quantite: Int,
//        val prix: Double
//    )


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val option_choisi = parent?.getItemAtPosition(position).toString()

        when(option_choisi){
            "Accueil"   -> findNavController().navigate(R.id.action_gestionProduit_to_epicerie_accueil)
            "Commandes" -> findNavController().navigate(R.id.action_gestionProduit_to_gestion_commandes)
            "Gabarits" -> findNavController().navigate(R.id.action_gestionProduit_to_gestion_gabarit)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    fun afficherDonnées(données: List<Produits>) {
        database = AppDatabase.getInstance(requireContext(), true)
        adapter = ProduitAdapter(données, requireContext(), database)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewProduits)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = adapter
    }

}
