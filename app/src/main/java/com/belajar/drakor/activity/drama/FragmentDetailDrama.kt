package com.belajar.drakor.activity.drama

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.belajar.drakor.R
import com.google.android.material.button.MaterialButton

class FragmentDetailDrama : Fragment() {

    private lateinit var buttonFavorite: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail_drama, container, false)

//        // Initialize the button
//        buttonFavorite = view.findViewById(R.id.button_favorite)
//        buttonFavorite.setOnClickListener {
//            // Handle favorite button click
//        }

        return view
    }
}