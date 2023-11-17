package com.example.gaspillezero.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gaspillezero.R

class PanierFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var adapter: PanierAdapter

    companion object {
        fun newInstance() = PanierFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_panier, container, false)
        val database = AppDatabase.getInstance(requireContext(), true)
        adapter = PanierAdapter(database.panierDAO().afficherItemsPanier(), database)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinner = view.findViewById<Spinner>(R.id.spinner)
        spinner.onItemSelectedListener = this

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_panierFragment_to_denreesFragment)
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewMagasin)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = adapter
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val option_choisi = parent?.getItemAtPosition(position).toString()

        when (option_choisi) {
            "Accueil"   -> findNavController().navigate(R.id.action_panierFragment_to_epicerie_accueil)
            "Denrées" -> findNavController().navigate(R.id.action_panierFragment_to_denreesFragment)
            "Épiceries" -> findNavController().navigate(R.id.action_panierFragment_to_fragment_epecerie)
            "Panier" -> findNavController().navigate(R.id.panierFragment)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}