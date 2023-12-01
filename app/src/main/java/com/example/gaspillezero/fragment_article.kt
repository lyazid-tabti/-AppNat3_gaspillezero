package com.example.gaspillezero

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_article.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_article : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_article, container, false)
        val nextBtn : Button = view.findViewById(R.id.btnArticle)
        nextBtn.setOnClickListener{
            val fragment = fragment_detail()
            val transcation = fragmentManager?.beginTransaction()
            transcation?.replace(R.id.container,fragment)?.commit()
        }
        return view
    }





}