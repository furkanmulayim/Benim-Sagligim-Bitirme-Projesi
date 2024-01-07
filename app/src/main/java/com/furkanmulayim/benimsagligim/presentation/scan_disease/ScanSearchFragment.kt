package com.furkanmulayim.benimsagligim.presentation.scan_disease

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.databinding.FragmentScanSearchBinding
import com.furkanmulayim.benimsagligim.util.showMessage

class ScanSearchFragment : Fragment() {

    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 100
        private const val GALLERY_PERMISSION_REQUEST_CODE = 101
    }

    private lateinit var cameraActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var galleryActivityResultLauncher: ActivityResultLauncher<Intent>

    private lateinit var binding: FragmentScanSearchBinding
    private lateinit var viewModel: ScanSearchViewModel

    private var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_scan_search, container, false)
        viewModel = ViewModelProvider(this)[ScanSearchViewModel::class.java]
        galleryResultListener()
        cameraResultListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        clickListeners()
    }

    private fun clickListeners() {
        binding.backButton.setOnClickListener {
            viewModel.navigate(requireView(), R.id.action_scanSearchFragment_to_searchFragment)
        }
        binding.cameraButton.setOnClickListener {
            pickImageCamera()
        }
        binding.galleryButton.setOnClickListener {
            pickImageGallery()
        }
    }

    fun cameraResultListener() {
        cameraActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                imageUriKaydet(imageUri.toString())
                Navigation.findNavController(requireView())
                    .navigate(R.id.action_scanSearchFragment_to_cropFragment)
            } else {
                message("Fotoğraf Çekilmedi!")
            }
        }
    }


    private fun requestPermissionCamera() {
        if (ContextCompat.checkSelfPermission(
                requireContext(), Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Eğer izin daha önce reddedildiyse
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(), Manifest.permission.CAMERA
                )
            ) {
                // Daha sonra izni tekrar istiyoruz
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.CAMERA),
                    CAMERA_PERMISSION_REQUEST_CODE
                )
            } else {
                // İzin daha önce hiç istenmediyse
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.CAMERA),
                    CAMERA_PERMISSION_REQUEST_CODE
                )
            }
        }
    }


    private fun pickImageCamera() {
        if (ContextCompat.checkSelfPermission(
                requireContext(), Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val values = ContentValues()
            values.put(MediaStore.Images.Media.TITLE, "Sample Title")
            values.put(MediaStore.Images.Media.DESCRIPTION, "Sample Description")
            imageUri = activity?.contentResolver?.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values
            )
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            cameraActivityResultLauncher.launch(intent)
        } else {
            requestPermissionCamera()
            message("Bu Özellik İçin İzne İhtiyacımız Var..")
        }
    }

    private fun galleryResultListener() {
        galleryActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data = result.data
                    if (data != null) {
                        imageUri = data.data
                        imageUriKaydet(imageUri.toString())
                        Navigation.findNavController(requireView())
                            .navigate(R.id.action_scanSearchFragment_to_cropFragment)
                    }
                } else {
                    message("Görsel Seçilmedi!")
                }
            }
    }

    private fun requestPermissionGallery() {
        if (ContextCompat.checkSelfPermission(
                requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Eğer izin daha önce reddedildiyse
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                // Daha sonra izni tekrar istiyoruz
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    GALLERY_PERMISSION_REQUEST_CODE
                )
            } else {
                // İzin daha önce hiç istenmediyse
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    GALLERY_PERMISSION_REQUEST_CODE
                )
            }
        }
    }


    private fun pickImageGallery() {
        if (ContextCompat.checkSelfPermission(
                requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            galleryActivityResultLauncher.launch(intent)
        } else {
            requestPermissionGallery()
            message("Bu Özellik İçin İzne İhtiyacımız Var..")
        }
    }

    private fun imageUriKaydet(imageUri: String) {
        viewModel.gorselUriKaydet(imageUri)
    }

    private fun message(message: String) {
        requireContext().showMessage(message)
    }
}