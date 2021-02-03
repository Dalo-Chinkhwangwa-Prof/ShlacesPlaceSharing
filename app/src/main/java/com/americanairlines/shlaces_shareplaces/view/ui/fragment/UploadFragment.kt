package com.americanairlines.shlaces_shareplaces.view.ui.fragment

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.americanairlines.shlaces_shareplaces.R
import com.americanairlines.shlaces_shareplaces.model.data.Shlace
import com.americanairlines.shlaces_shareplaces.viewmodel.ShlaceViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream

class UploadFragment : Fragment() {

    private lateinit var uploadButton: Button
    private lateinit var placeImageView: ImageView
    private lateinit var addressEditText: EditText
    private lateinit var descriptionEditText: EditText

    private var shlaceBitmap: Bitmap? = null

    private val shlaceViewModel: ShlaceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.upload_fragment_layout, container, false)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val backPressedCallback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            parentFragment?.childFragmentManager?.popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        placeImageView = view.findViewById(R.id.upload_imageview)

        uploadButton = view.findViewById(R.id.upload_button)
        addressEditText = view.findViewById(R.id.address_edittext)
        descriptionEditText = view.findViewById(R.id.description_edittext)

        placeImageView.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, 111)
        }

        uploadButton.setOnClickListener {

            shlaceBitmap?.let {

                val bOS = ByteArrayOutputStream()
                it.compress(Bitmap.CompressFormat.JPEG, 100, bOS)
                val imageBytes = bOS.toByteArray()

                val userId = FirebaseAuth.getInstance().currentUser?.uid ?: "default"

                val storageReference = FirebaseStorage.getInstance()
                    .reference.child(userId)

                val uploadTask = storageReference.putBytes(imageBytes)

                uploadTask.addOnCompleteListener {

                    if (it.isSuccessful) {

                        storageReference
                            .downloadUrl //File url of where the file was uploaded: IOW the image url
                            .addOnCompleteListener { dlTask ->
                                if (dlTask.isSuccessful)
                                    upload(dlTask.result)
                            }

                    }

                }

            } ?: { Toast.makeText(context, "Must have a picture.", Toast.LENGTH_SHORT).show() }()

        }
    }

    private fun upload(result: Uri?) {
        val userEmail = FirebaseAuth.getInstance().currentUser?.email ?: "unknown"
        val location = addressEditText.text.toString().trim()
        val description = descriptionEditText.text.toString().trim()
        val shlace = Shlace().also {
            it.imageUrl = result.toString()
            it.postedBy = userEmail
            it.description = description
            it.address = location
        }
        shlaceViewModel.uploadShlace(shlace)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        shlaceBitmap = data?.extras?.get("data") as Bitmap?
        shlaceBitmap?.let {
            placeImageView.setImageBitmap(it)
        }
    }


}









