package com.furkanmulayim.benimsagligim.presentation.information

import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.databinding.FragmentInformationBinding
import com.furkanmulayim.benimsagligim.util.showMessage
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import jp.wasabeef.blurry.Blurry

class InformationFragment : Fragment() {

    private lateinit var binding: FragmentInformationBinding
    private lateinit var viewModel: InformationViewModel
    private lateinit var textRecognizer: TextRecognizer
    private lateinit var webView: WebView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_information, container, false)
        viewModel = ViewModelProvider(this)[InformationViewModel::class.java]
        textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        observeText()
        viewModel.getSorgu()
        observeGptResponse()
        clickListener()
    }

    private fun observeText() {
        viewModel.textMed.observe(viewLifecycleOwner, Observer {
            recognizeTextFromImage(viewModel.parsUrl(it))
            getImageUri(it)
        })
    }

    private fun clickListener() {
        binding.gptButton.setOnClickListener {
            gptSor()
            binding.webViewLay.visibility = View.VISIBLE
            webView(binding.ilacEditText.text.toString())
            binding.gptResponse.text = getString(R.string.sabir)
        }
    }

    private fun getImageUri(it:String) {
        var imageUri = viewModel.parsUrl(it)
        bulanikYap(imageUri, binding.bulanikImageView)
    }
    fun bulanikYap(url: Uri, imageView: ImageView) {
        val bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, url)
        // Blurry kütüphanesi kullanarak bulanıklaştırma işlemi
        Blurry.with(requireContext()).radius(8).sampling(2).from(bitmap).into(imageView)

    }
    private fun gptSor() {
        viewModel.getGPT(binding.ilacEditText.text.toString())
    }

    private fun webView(searchQuery: String) {
        webView = binding.webView
        val googleSearchUrl =
            "https://www.google.com.tr/search?q=$searchQuery+hangi+tedavide+kullanılır?"
        webView.loadUrl(googleSearchUrl)
    }

    private fun observeGptResponse() {
        viewModel.getGPTResponseLiveData().observe(viewLifecycleOwner, Observer {
            it.let {
                binding.gptResponse.text = it.trimStart()

            }
        })
    }

    private fun recognizeTextFromImage(resim: Uri) {
        try {
            val inputImage = resim.let { InputImage.fromFilePath(requireContext(), it) }
            val textTaskResult =
                inputImage.let { textRecognizer.process(it) }.addOnSuccessListener { _text ->
                    binding.ilacEditText.setText(_text.text)
                }.addOnFailureListener { ex ->
                    requireActivity().showMessage(getString(R.string.bir_sorun_var))
                }
        } catch (e: java.lang.Exception) {
            requireActivity().showMessage(getString(R.string.bir_sorun_var))
        }
    }


}