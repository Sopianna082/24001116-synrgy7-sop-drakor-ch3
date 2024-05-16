package com.belajar.drakor.activity.drama

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.belajar.drakor.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val drama = intent.getParcelableExtra<Drama>(EXTRA_DRAMA)
        if (drama != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.containerFragmentDetail, DramaDetailFragment.newInstance(drama))
                .commit()
        } else {
            // Handle the case where Drama object is not passed correctly
            // or handle the error accordingly
        }
    }

    companion object {
        const val EXTRA_DRAMA = "extra_drama"
    }
}
