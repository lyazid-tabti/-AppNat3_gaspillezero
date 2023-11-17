package com.example.gaspillezero.ui.main.PrésentationDenrées

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gaspillezero.R
import com.example.gaspillezero.ui.main.PrésentationGabarits.GabaritAdapter
import com.example.gaspillezero.ui.main.PrésentationGabarits.GabaritsPrésenteur
import com.example.gaspillezero.ui.main.sourceDeDonnées.Gabarits
import androidx.navigation.fragment.findNavController

class GabaritFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var adapter: GabaritAdapter
    private var présentateur = GabaritsPrésenteur(this)

    companion object {
        fun newInstance() = GabaritFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_gestion_gabarit, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinner = view.findViewById<Spinner>(R.id.spinner)
        spinner.onItemSelectedListener = this
        présentateur.obtenirDonnées()

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val option_choisi = parent?.getItemAtPosition(position).toString()

        when (option_choisi) {
            "Accueil"   -> findNavController().navigate(R.id.action_denreesFragment_to_epicerie_accueil)
            "Denrées" -> findNavController().navigate(R.id.denreesFragment)
            "Épiceries" -> findNavController().navigate(R.id.action_denreesFragment_to_fragment_epecerie)
            "Panier"    -> findNavController().navigate(R.id.action_denreesFragment_to_panierFragment)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    fun afficherDonnées(données: List<Gabarits>) {
        adapter = GabaritAdapter(données)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewGabarits)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = adapter
    }

}
