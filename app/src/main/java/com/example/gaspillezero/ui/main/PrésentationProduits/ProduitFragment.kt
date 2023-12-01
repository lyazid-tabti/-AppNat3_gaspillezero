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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gaspillezero.R
import com.example.gaspillezero.ui.main.DossierPanier.AppDatabase
import com.example.gaspillezero.ui.main.PrésentationDenrées.DenréesAdapter
import com.example.gaspillezero.ui.main.sourceDeDonnées.Produits
import kotlinx.coroutines.launch

class ProduitFragment : Fragment(), AdapterView.OnItemSelectedListener {

    var présentateur = ProduitPrésentateur(this)
    private lateinit var adapter: ProduitAdapter
    private lateinit var database: AppDatabase

    companion object {
        fun newInstance() = ProduitFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gestion_produit, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinner = view.findViewById<Spinner>(R.id.spinner2)
        spinner.onItemSelectedListener = this

        lifecycleScope.launch {
            présentateur.obtenirDonnées()
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_gestionProduit_to_epicerie_accueil)
        }
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val option_choisi = parent?.getItemAtPosition(position).toString()

        when(option_choisi){
            "Accueil"   -> findNavController().navigate(R.id.action_gestionProduit_to_epicerie_accueil)
            "Commandes" -> findNavController().navigate(R.id.action_gestionProduit_to_commandeFragment)
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
