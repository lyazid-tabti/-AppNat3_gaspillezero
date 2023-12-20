package com.example.gaspillezero

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.StrictMode
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.addCallback
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gaspillezero.ui.main.DossierPanier.MyDatabase
import com.example.gaspillezero.ui.main.PrésentationMagasin.MagasinAdapter
import com.example.gaspillezero.ui.main.PrésentationMagasin.MagasinPrésentateur
import com.example.gaspillezero.ui.main.sourceDeDonnées.Magasins
import com.example.gaspillezero.ui.main.sourceDeDonnées.Épicerie

import kotlinx.coroutines.launch

class fragment_epecerie : Fragment(), AdapterView.OnItemSelectedListener {
    var présentateur = MagasinPrésentateur(this)
    private var listemagasin = mutableListOf<Épicerie>()
    private lateinit var adapter: MagasinAdapter
    private lateinit var database: MyDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_epecerie, container, false)

        /*val nextBtn : Button = view.findViewById(R.id.btnRetour)
        val btnarticle : Button = view.findViewById(R.id.btnarticle)

        nextBtn.setOnClickListener{
            val fragment = fragment_detail()
            val transcation = fragmentManager?.beginTransaction()
            transcation?.replace(R.id.container,fragment)?.commit()
        }
        btnarticle.setOnClickListener{
            val fragment = fragment_article()
            val transcation1 = fragmentManager?.beginTransaction()
            transcation1?.replace(R.id.container,fragment)?.commit()
        }
        btnarticle.setOnClickListener{
            //findNavController().navigate(R.id.action_fragment_epecerie_to_denreesFragment)
        }
        val ButtonRetourne = view.findViewById<Button>(R.id.btnRetour)
        ButtonRetourne.setOnClickListener{
            findNavController().navigate(R.id.action_fragment_epecerie_to_epicerie_accueil)
        }*/
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val SDK_INT: Int = android.os.Build.VERSION.SDK_INT
        if (SDK_INT > 8) {
            val policy: StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder()
                .permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
        super.onViewCreated(view, savedInstanceState)

        val spinner = view.findViewById<Spinner>(R.id.spinner)
        spinner.onItemSelectedListener = this

        lifecycleScope.launch  {
            présentateur.obtenirDonnéesÉpeceries()
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_fragment_epecerie_to_epicerie_accueil)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val option_choisi = parent?.getItemAtPosition(position).toString()

        when (option_choisi) {
            "Accueil"   -> findNavController().navigate(R.id.action_fragment_epecerie_to_epicerie_accueil)
            "Denrées" -> findNavController().navigate(R.id.action_fragment_epecerie_to_denreesFragment)
            "Épiceries" -> findNavController().navigate(R.id.fragment_epecerie)
            "Panier" -> findNavController().navigate(R.id.action_fragment_epecerie_to_panierFragment)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    fun afficherDonnées(données: List<Épicerie>) {
        database = MyDatabase.getInstance(requireContext(), true)
        adapter = MagasinAdapter(données)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerViewMagasin)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = adapter
    }
}