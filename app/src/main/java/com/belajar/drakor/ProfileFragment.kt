package com.belajar.drakor

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
//import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
//import android.widget.ImageView
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    // Request code untuk membuka kamera
    private val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Menginisialisasi tombol
        val openCameraButton: Button = view.findViewById(R.id.openCameraButton)

        // Memberikan listener untuk tombol
        openCameraButton.setOnClickListener {
            dispatchTakePictureIntent()
        }

        return view
    }

    // Method untuk membuka kamera
    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    // Mendapatkan hasil dari pengambilan gambar
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            //Menampilkan gambar
        }
    }
}

