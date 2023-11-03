package com.example.gaspillezero

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_epecerie.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_epecerie : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_epecerie, container, false)

        val nextBtn : Button = view.findViewById(R.id.btnRetour)
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
        return view
    }




}