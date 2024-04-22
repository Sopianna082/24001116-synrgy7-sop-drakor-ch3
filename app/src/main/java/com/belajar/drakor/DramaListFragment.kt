package com.belajar.drakor

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DramaListFragment : Fragment(), OnItemClickListener {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_drama_list, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewDramaList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = DramaAdapter(getDramaList(), this)
        return view
    }

//    override fun onItemClick(drama: Drama) {
//        val intent = Intent(requireContext(), DramaDetailFragment::class.java)
//        intent.putExtra("DRAMA_TITLE", drama.title)
//        intent.putExtra("DRAMA_IMAGE_URL", drama.imageUrl)
//        startActivity(intent)
//    }

    override fun onItemClick(drama: Drama) {
        val detailFragment = DramaDetailFragment.newInstance(drama)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, detailFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun getDramaList(): List<Drama> {
        return listOf(
            Drama("Crash Landing on You",
                "https://i.pinimg.com/236x/fa/cf/fd/facffd892be28a9ce217b16e6107952e.jpg"),
            Drama("Descendants of the Sun",
                "https://i.pinimg.com/236x/65/5a/c4/655ac4615531b39f16f8a3aa5056aef5.jpg"),
            Drama("Goblin",
                "https://i.pinimg.com/236x/9a/1d/f1/9a1df1002d29d5bd1dddf5678498161c.jpg"),
            Drama("Itaewon Class",
                "https://i.pinimg.com/236x/60/50/55/605055d4d2831f23fe0b6cb3282859bf.jpg"),
            Drama("Reply 1988",
                "https://i.pinimg.com/236x/bf/01/d4/bf01d49fdbb244a2c422910b3a2dd4bc.jpg"),
            Drama("Lovely Runner",
                "https://i.pinimg.com/474x/03/d6/f5/03d6f5731b89df0eefd9908c9bb714e7.jpg"),
            Drama("Queen of Tears",
                "https://i.pinimg.com/474x/1a/e2/12/1ae2124230d388de36b625bdf80e427e.jpg"),
            Drama("A Shop For Killers",
                "https://i.pinimg.com/474x/15/18/73/151873efb0aaa2547a8f5ba8f8b2964a.jpg"),
            Drama("Extraordinary You",
                "https://i.pinimg.com/736x/4b/4a/3a/4b4a3aab3d560140167cd35891fd8c93.jpg")
        )
    }
}
