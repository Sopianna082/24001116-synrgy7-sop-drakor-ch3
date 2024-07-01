package com.belajar.drakor.activity.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.belajar.drakor.activity.MyApplication
import com.belajar.drakor.databinding.FragmentUserBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private val viewModel: UserViewModel by viewModel()
//    private val viewModel: UserViewModel by viewModels {
//        UserViewModelFactory((requireActivity().application as MyApplication).repository)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("UserFragment", "onCreateView called")
        binding = FragmentUserBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = UserAdapter { user -> viewModel.deleteUser(user) }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.allUsers.observe(viewLifecycleOwner, Observer { users ->
            users?.let { adapter.submitList(it) }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("UserFragment", "onViewCreated called")
    }

    override fun onStart() {
        super.onStart()
        Log.d("UserFragment", "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("UserFragment", "onResume called")
    }
}
