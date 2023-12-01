package com.example.gaspillezero.ui.main.PrésentationCommandes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gaspillezero.R
import com.example.gaspillezero.ui.main.DossierPanier.AppDatabase
import com.example.gaspillezero.ui.main.sourceDeDonnées.Commandes
import com.example.gaspillezero.ui.main.PrésentationCommandes.CommandeAdapter
import kotlinx.coroutines.launch

class CommandeFragment : Fragment(), AdapterView.OnItemSelectedListener{

    var présentateur = CommandePrésentateur(this)
    private lateinit var adapter: CommandeAdapter
    private lateinit var database: AppDatabase

    companion object{
        fun newInstance() = CommandeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gestion_commandes, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinner = view.findViewById<Spinner>(R.id.spinner3)
        spinner.onItemSelectedListener = this

        lifecycleScope.launch {
            présentateur.obtenirDonnées()
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_commandeFragment_to_epicerie_accueil)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val option_choisi = parent?.getItemAtPosition(position).toString()

        when(option_choisi){
            "Accueil"   -> findNavController().navigate(R.id.action_commandeFragment_to_epicerie_accueil)
            "Produits" -> findNavController().navigate(R.id.action_commandeFragment_to_gestionProduit)
            "Gabarits" -> findNavController().navigate(R.id.action_commandeFragment_to_gestion_gabarit)
        }    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    fun afficherDonnées(données: List<Commandes>){
        database = AppDatabase.getInstance(requireContext(),true)
        adapter = CommandeAdapter(données,requireContext(),database)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewCommandes)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = adapter
    }

}