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
import com.example.gaspillezero.ui.main.PrésentationGabarits.GabaritsPrésenteur
import com.example.gaspillezero.ui.main.sourceDeDonnées.Gabarits

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
        return inflater.inflate(R.layout.fragment_gestion_gabarit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        présentateur.obtenirDonnées()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val option_choisi = parent?.getItemAtPosition(position).toString()
        // Logique à ajouter si nécessaire
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onResume() {
        super.onResume()
        présentateur.obtenirDonnées() // Rafraîchir les données
    }

    fun afficherDonnées(données: List<Gabarits>) {
        adapter = GabaritAdapter(données.toMutableList()) { gabarit ->
            // Appel à la méthode de suppression dans le présentateur
            présentateur.supprimerGabarit(gabarit)

            // Suppression de l'élément dans l'adapter
            adapter.removeItem(gabarit)
        }

        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewGabarits)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = adapter
    }
}
