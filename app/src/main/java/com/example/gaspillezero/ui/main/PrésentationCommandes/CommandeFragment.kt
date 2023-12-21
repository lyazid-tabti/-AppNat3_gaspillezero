package com.example.gaspillezero.ui.main.PrésentationCommandes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gaspillezero.R
import com.example.gaspillezero.ui.main.sourceDeDonnées.Commandes
import com.example.gaspillezero.ui.main.PrésentationCommandes.*


class CommandeFragment : Fragment(), AdapterView.OnItemSelectedListener,CommandeVue{

    private lateinit var adapter: CommandeAdapter
    private lateinit var présentateur: CommandesPrésentateur

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        présentateur = CommandePrésentateur(this, CommandeModèle())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gestion_commandes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(view)
        présentateur.obtenirDonnées()
    }

    private fun setupRecyclerView(view: View) {
        adapter = CommandeAdapter(mutableListOf(),
            onDeleteClick = { commande ->
                présentateur.supprimerCommande(commande)
            }
        )

        view.findViewById<RecyclerView>(R.id.recyclerViewCommandes).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@CommandeFragment.adapter
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun afficherDonnées(données: List<Commandes>) {
        adapter.setCommandes(données)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        // peut ajouter logique ici
    }

    override fun onResume() {
        super.onResume()
        présentateur.obtenirDonnées()
    }
}