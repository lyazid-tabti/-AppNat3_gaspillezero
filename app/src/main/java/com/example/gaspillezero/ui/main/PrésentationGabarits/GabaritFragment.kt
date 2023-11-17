package com.example.gaspillezero.ui.main.PrésentationDenrées

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gaspillezero.R
import com.example.gaspillezero.ui.main.PrésentationGabarits.GabaritAdapter
import com.example.gaspillezero.ui.main.PrésentationGabarits.GabaritPrésentateur
import com.example.gaspillezero.ui.main.PrésentationGabarits.GabaritVue
import com.example.gaspillezero.ui.main.PrésentationGabarits.GabaritsPrésenteur
import com.example.gaspillezero.ui.main.sourceDeDonnées.Gabarits

class GabaritFragment : Fragment(), AdapterView.OnItemSelectedListener, GabaritVue {

    private lateinit var adapter: GabaritAdapter
    private lateinit var présentateur: GabaritPrésentateur

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        présentateur = GabaritsPrésenteur(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_gestion_gabarit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(view)
        présentateur.obtenirDonnées()
    }

    private fun setupRecyclerView(view: View) {
        adapter = GabaritAdapter(mutableListOf()) { gabarit ->
            présentateur.supprimerGabarit(gabarit)
        }

        view.findViewById<RecyclerView>(R.id.recyclerViewGabarits).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@GabaritFragment.adapter
        }
    }

    override fun afficherDonnées(données: List<Gabarits>) {
        adapter.setGabarits(données)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        // Logique à ajouter si nécessaire
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onResume() {
        super.onResume()
        présentateur.obtenirDonnées() // Rafraîchir les données
    }
}
