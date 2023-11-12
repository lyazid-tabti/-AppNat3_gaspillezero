package com.example.gaspillezero.ui.main.PrésentationDenrées

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gaspillezero.R
import com.example.gaspillezero.ui.main.sourceDeDonnées.Produits

class DenreesFragment : Fragment() {

    var présentateur = DenréesPrésentateur(this)
    private lateinit var adapter: DenréesAdapter

    companion object {
        fun newInstance() = DenreesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_denrees, container, false)
        val panierTextView = view.findViewById<TextView>(R.id.panier)

        panierTextView.setOnClickListener {
            findNavController().navigate(R.id.action_denreesFragment_to_panierFragment)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        présentateur.obtenirDonnées()
    }

    fun afficherDonnées(données: List<Produits>) {
        adapter = DenréesAdapter(données)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewDenrées)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = adapter
    }
}

