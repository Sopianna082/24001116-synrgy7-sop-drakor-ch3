//package com.belajar.drakor.activity.drama
//
//import android.content.Intent
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.databinding.DataBindingUtil
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.belajar.drakor.R
//import com.belajar.drakor.activity.drama.adapter.DramaAdapter
//import com.belajar.drakor.activity.drama.adapter.OnItemClickListener
//import com.belajar.drakor.databinding.FragmentFavoriteListBinding
//
//class FavoriteListFragment : Fragment(), OnItemClickListener {
//
//    private lateinit var viewModel: DramaViewModel
//    private lateinit var dramaAdapter: DramaAdapter
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val binding: FragmentFavoriteListBinding = DataBindingUtil.inflate(
//            inflater, R.layout.fragment_favorite_list, container, false
//        )
//
//        viewModel = ViewModelProvider(this).get(DramaViewModel::class.java)
//        binding.viewModel = viewModel
//        binding.lifecycleOwner = this
//
//        dramaAdapter = DramaAdapter(this)
//        binding.recyclerViewFavoriteList.apply {
//            layoutManager = LinearLayoutManager(context)
//            adapter = dramaAdapter
//        }
//
//        viewModel.favoriteList.observe(viewLifecycleOwner) {
//            dramaAdapter.submitList(it)
//        }
//
//        return binding.root
//    }
//
//    override fun onItemClick(drama: Drama) {
//        val intent = Intent(requireContext(), DetailActivity::class.java).apply {
//            putExtra(DetailActivity.EXTRA_DRAMA, drama)
//        }
//        startActivity(intent)
//    }
//
//    override fun onFavoriteClick(drama: Drama) {
//        viewModel.addOrRemoveFavorite(drama)
//    }
//}
//
