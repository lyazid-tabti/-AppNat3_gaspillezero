package com.example.gaspillezero

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [liste_magasin.newInstance] factory method to
 * create an instance of this fragment.
 */
class liste_magasin : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_liste_magasin, container, false)
        //val btnarticle : Button = view.findViewById(R.id.btnArticle)
        /*btnarticle.setOnClickListener{
            val fragment = fragment_detail()
            val transcation = fragmentManager?.beginTransaction()
            transcation?.replace(R.id.container,fragment)?.commit()
        }*/

        val ButtonArticle = view.findViewById<Button>(R.id.btnArticle)
        ButtonArticle.setOnClickListener{
            findNavController().navigate(R.id.action_fragment_epecerie_to_denreesFragment)
        }
        return view
    }


}