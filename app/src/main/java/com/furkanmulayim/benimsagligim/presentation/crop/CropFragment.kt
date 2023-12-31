package com.furkanmulayim.benimsagligim.presentation.crop

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.databinding.FragmentCropBinding
import com.furkanmulayim.benimsagligim.util.showMessage
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView

class CropFragment : Fragment() {

    private lateinit var binding: FragmentCropBinding
    private lateinit var viewModel: CropViewModel
    private lateinit var imageUri: Uri

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_crop, container, false)
        viewModel = ViewModelProvider(this)[CropViewModel::class.java]
        getImageUri()
        goingForCrop()
        return binding.root
    }

    private fun getImageUri() {
        imageUri = Uri.parse(viewModel.getUrl())
        binding.imageIv.setImageURI(imageUri)
    }

    private fun goingForCrop() {
        val cropImageIntent =
            CropImage.activity(imageUri).setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true).getIntent(requireContext())
        startActivityForResult(cropImageIntent, 1001)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1001) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {
                val croppedUri = result.uri
                // Kırpılmış resmi kullanın
                viewModel.setImageUri(croppedUri.toString())
                passForNAvigation(R.id.action_cropFragment_to_recognitionFragment)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
                requireActivity().showMessage("Resim Kırpılamadı Tekrar Deneyin! " + error.localizedMessage)
            }
        }
    }

    private fun passForNAvigation(pageId: Int) {
        viewModel.navigate(requireView(), pageId)
    }

}