package com.furkanmulayim.benimsagligim.presentation.pill_future

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
import com.furkanmulayim.benimsagligim.databinding.FragmentPillInformationBinding
import com.furkanmulayim.benimsagligim.util.showMessage
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView

class PillInformationFragment : Fragment() {

    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 100
        private const val GALLERY_PERMISSION_REQUEST_CODE = 101
        private const val CROP_IMAGE_REQUEST_CODE = 1001
    }

    private lateinit var cameraActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var galleryActivityResultLauncher: ActivityResultLauncher<Intent>

    private lateinit var binding: FragmentPillInformationBinding
    private lateinit var viewModel: PillViewModel

    private var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_pill_information, container, false)
        viewModel = ViewModelProvider(this)[PillViewModel::class.java]
        cameraResultListener()
        galleryResultListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        clickListeners()
    }

    private fun clickListeners() {
        binding.backButton.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_pillInformationFragment_to_homeFragment)
        }

        binding.cameraButton.setOnClickListener {
            pickImageCamera()
        }

        binding.galleryButton.setOnClickListener {
            pickImageGallery()
        }
    }

    private fun cameraResultListener() {
        cameraActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                imageUriKaydet(imageUri.toString())
                goingForCrop()
            } else {
                message(getString(R.string.cekilmedi))
            }
        }
    }

    private fun requestPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(
                requireContext(), permission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val rationale = ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(), permission
            )

            val requestPermission = ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(permission), requestCode
            )

            if (rationale) {
                requestPermission
            } else {
                requestPermission
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
            message(getString(R.string.izinn))
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
                        goingForCrop()
                    }
                } else {
                    message(getString(R.string.secilmedi))
                }
            }
    }

    private fun requestPermissionGallery() {
        requestPermission(
            Manifest.permission.READ_EXTERNAL_STORAGE, GALLERY_PERMISSION_REQUEST_CODE
        )
    }

    private fun requestPermissionCamera() {
        requestPermission(
            Manifest.permission.CAMERA, CAMERA_PERMISSION_REQUEST_CODE
        )
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
            message(getString(R.string.izinn))
        }
    }

    private fun goingForCrop() {
        val cropImageIntent =
            CropImage.activity(imageUri).setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true).getIntent(requireContext())
        startActivityForResult(cropImageIntent, CROP_IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CROP_IMAGE_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                val croppedUri = result.uri
                imageUriKaydet(croppedUri.toString())
                navigate(R.id.action_pillInformationFragment_to_informationFragment)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
                requireActivity().showMessage("Resim Kırpılamadı Tekrar Deneyin! " + error.localizedMessage)
            }
        }
    }

    private fun imageUriKaydet(imageUri: String) {
        viewModel.gorselUriKaydet(imageUri)
    }

    private fun message(message: String) {
        requireContext().showMessage(message)
    }

    private fun navigate(action:Int){
        viewModel.navigate(requireView(),action)
    }
}