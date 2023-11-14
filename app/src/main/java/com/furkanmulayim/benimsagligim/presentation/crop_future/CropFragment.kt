package com.furkanmulayim.benimsagligim.presentation.crop_future

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.databinding.FragmentCropBinding
import com.furkanmulayim.benimsagligim.util.SharedPrefs
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView

class CropFragment : Fragment() {

    private lateinit var binding: FragmentCropBinding
    private var sp = SharedPrefs()
    private lateinit var imageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_crop, container, false)
        getImageUri()
        cropClicked()
        dontCropClicked()
        return binding.root
    }

    private fun getImageUri() {
        val imageUriString = sp.getImageUriInShared()
        if (!imageUriString.isNullOrEmpty()) {
            imageUri = Uri.parse(imageUriString)
            binding.imageIv.setImageURI(imageUri)
        }

    }

    private fun dontCropClicked() {
        binding.imageDontCrop.setOnClickListener {
            //Navigation.findNavController(requireView()).navigate(R.id.action_cropFragment_to_recognitionFragment)
            println("Furkan : " + "Kırpılmadı")
        }
    }


    private fun cropClicked() {
        binding.imageCrop.setOnClickListener {
            val cropImageIntent =
                CropImage.activity(imageUri).setGuidelines(CropImageView.Guidelines.ON)
                    .setMultiTouchEnabled(true).getIntent(requireContext())
            startActivityForResult(cropImageIntent, 1001)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1001) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {
                val croppedUri = result.uri
                // Kırpılmış resmi kullanın
                sp.saveImageLocation(croppedUri.toString())
                //getImageUri()
                Navigation.findNavController(requireView()).navigate(R.id.action_cropFragment_to_searchFragment)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
                // Hata durumu
            }
        }
    }

}