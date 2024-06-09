package com.belajar.drakor.retrofit

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.belajar.drakor.databinding.ActivityPeopleListBinding
import android.util.Log

class PeopleListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPeopleListBinding
    private val viewModel: PeopleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPeopleListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewPeople.layoutManager = LinearLayoutManager(this)

        viewModel.getPopularPeople().observe(this, Observer { people ->
            if (people != null) {
                binding.recyclerViewPeople.adapter = PeopleAdapter(people)
                Log.e("PeopleListActivity", "Data people exist: ${people.size} people loaded")
            } else {
                Log.e("PeopleListActivity", "Received null people list from ViewModel")
            }
        })
    }
}
