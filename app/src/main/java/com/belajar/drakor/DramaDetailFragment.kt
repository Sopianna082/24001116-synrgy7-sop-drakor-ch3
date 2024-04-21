package com.belajar.drakor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import coil.load

class DramaDetailFragment : Fragment() {

    companion object {
        private const val ARG_DRAMA = "arg_drama"

        fun newInstance(drama: Drama): DramaDetailFragment {
            val fragment = DramaDetailFragment()
            val args = Bundle()
            args.putParcelable(ARG_DRAMA, drama)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_drama, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val drama = arguments?.getParcelable<Drama>(ARG_DRAMA)
        drama?.let {
            // Mengisi tampilan dengan informasi drama
            val imageViewPoster: ImageView = view.findViewById(R.id.imageViewPosterDetail)
            val textViewTitle: TextView = view.findViewById(R.id.textViewTitleDetail)

            textViewTitle.text = it.title
            imageViewPoster.load(it.imageUrl) {
                placeholder(R.drawable.placeholder) // Placeholder image while loading
                error(R.drawable.error) // Image to display if loading fails
            }
        }
    }
}

