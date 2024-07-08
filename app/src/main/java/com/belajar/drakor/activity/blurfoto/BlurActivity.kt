package com.belajar.drakor.activity.blurfoto

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.WorkInfo
import com.belajar.drakor.R
import com.belajar.drakor.databinding.ActivityBlurBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class BlurActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBlurBinding
    private val viewModel: BlurViewModel by viewModel()
    private var selectedImage: Bitmap? = null

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            val inputStream = contentResolver.openInputStream(it)
            selectedImage = BitmapFactory.decodeStream(inputStream)
            binding.imageViewSelected.setImageBitmap(selectedImage)
        }
    }

    private val takePhoto = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        bitmap?.let {
            selectedImage = it
            binding.imageViewSelected.setImageBitmap(selectedImage)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlurBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonPickFile.setOnClickListener {
            getContent.launch("image/*")
        }

        binding.buttonTakePhoto.setOnClickListener {
            takePhoto.launch(null)
        }

        binding.buttonApplyBlur.setOnClickListener {
            selectedImage?.let { image ->
                val blurLevel = when (binding.radioGroupBlurLevel.checkedRadioButtonId) {
                    R.id.radioLevel1 -> 1
                    R.id.radioLevel2 -> 2
                    R.id.radioLevel3 -> 3
                    else -> 1
                }
                viewModel.applyBlur(image, blurLevel)
            }
        }

        viewModel.outputBitmap.observe(this, Observer { bitmap ->
            binding.imageViewSelected.setImageBitmap(bitmap)
        })

        viewModel.workInfo.observe(this, Observer { workInfo ->
            if (workInfo != null) {
                when (workInfo.state) {
                    WorkInfo.State.RUNNING -> Toast.makeText(this, "Blurring in progress...", Toast.LENGTH_SHORT).show()
                    WorkInfo.State.SUCCEEDED -> Toast.makeText(this, "Blur successful!", Toast.LENGTH_SHORT).show()
                    WorkInfo.State.FAILED -> Toast.makeText(this, "Blur failed!", Toast.LENGTH_SHORT).show()
                    else -> Unit
                }
            }
        })

        binding.buttonViewBlurredImage.setOnClickListener {
            val uri = viewModel.getBlurredImageUri()
            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(uri, "image/png")
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            }
            startActivity(intent)
        }
    }
}