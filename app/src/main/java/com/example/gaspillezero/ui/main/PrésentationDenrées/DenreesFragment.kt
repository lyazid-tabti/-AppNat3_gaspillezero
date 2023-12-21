package com.example.gaspillezero.ui.main.PrésentationDenrées

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gaspillezero.R
import com.example.gaspillezero.ui.main.DossierPanier.MyDatabase
import com.example.gaspillezero.ui.main.sourceDeDonnées.Produits
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DenreesFragment : Fragment(), AdapterView.OnItemSelectedListener {

    var présentateur = DenréesPrésentateur(this)
    private lateinit var adapter: DenréesAdapter
    private lateinit var database: MyDatabase

    companion object {
        fun newInstance() = DenreesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_denrees, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinner = view.findViewById<Spinner>(R.id.spinner)
        val progression = view.findViewById<ProgressBar>(R.id.barProgression)
        val message = view.findViewById<TextView>(R.id.messageErreur)
        spinner.onItemSelectedListener = this

            val connection = estConnecté()
            try {
                message.visibility = View.GONE
                progression.visibility = View.VISIBLE
                //delay(1200)
                présentateur.obtenirDonnées()
                progression.visibility = View.GONE
            } catch(e:Exception){
                if (connection == false){
                    progression.visibility = View.GONE
                    message.visibility = View.VISIBLE
                }
            }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_denreesFragment_to_fragment_epecerie)
        }
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

    fun afficherDonnées(données: List<Produits>) {
        database = MyDatabase.getInstance(requireContext(), true)
        adapter = DenréesAdapter(données, requireContext(), database)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewDenrées)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = adapter
    }

    private fun estConnecté(): Boolean {
        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}

