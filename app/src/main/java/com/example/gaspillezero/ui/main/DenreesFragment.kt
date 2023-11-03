package com.example.gaspillezero.ui.main

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gaspillezero.R


class DenreesFragment : Fragment() {

    companion object {
        fun newInstance() = DenreesFragment()
    }

    private lateinit var viewModel: DenreesViewModel

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DenreesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_denrees, container, false)
        val ButtonAjout = view.findViewById<Button>(R.id.btnAjout)
        ButtonAjout.setOnClickListener{
            findNavController().navigate(R.id.action_denreesFragment_to_fragment_detail)
        }
        val panierTextView = view.findViewById<TextView>(R.id.panier)
        panierTextView.setOnClickListener {
            findNavController().navigate(R.id.action_denreesFragment_to_panierFragment)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val panierTextView = view.findViewById<TextView>(R.id.panier)

        /*panierTextView.setOnClickListener {
            val action = DenreesFragmentDirections.actionDenreesFragmentToPanierFragment()
            findNavController().navigate(action)
        }*/
    }
}
