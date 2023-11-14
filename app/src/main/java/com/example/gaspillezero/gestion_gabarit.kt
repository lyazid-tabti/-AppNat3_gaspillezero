package com.example.gaspillezero

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [gestion_gabarit.newInstance] factory method to
 * create an instance of this fragment.
 */
class gestion_gabarit : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_gestion_gabarit, container, false)

        val ButtonAccueil = view.findViewById<ImageButton>(R.id.Accueil)
        ButtonAccueil.setOnClickListener{
            findNavController().navigate(R.id.action_gestion_gabarit_to_epicerie_accueil)
        }
        val ButtonDetail1 = view.findViewById<ImageButton>(R.id.Detail1)
        ButtonDetail1.setOnClickListener{
            findNavController().navigate(R.id.action_gestion_gabarit_to_detail_gabarit1)
        }
        val ButtonDetail2 = view.findViewById<ImageButton>(R.id.Detail2)
        ButtonDetail2.setOnClickListener{
            findNavController().navigate(R.id.action_gestion_gabarit_to_detail_gabarit2)
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment gestion_gabarit.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            gestion_gabarit().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}