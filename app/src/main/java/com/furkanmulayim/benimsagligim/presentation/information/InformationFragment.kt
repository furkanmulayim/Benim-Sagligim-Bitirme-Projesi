package com.furkanmulayim.benimsagligim.presentation.information

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.databinding.FragmentInformationBinding
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

class InformationFragment : Fragment() {

    private lateinit var binding: FragmentInformationBinding
    private lateinit var viewModel: InformationViewModel
    private lateinit var textRecognizer: TextRecognizer
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
        })
    }

    private fun clickListener() {
        binding.gptButton.setOnClickListener {
            gptSor()
            binding.gptResponse.text = getString(R.string.sabir)
        }
    }

    private fun gptSor() {
        viewModel.getGPT(binding.ilacEditText.text.toString())
    }

    private fun observeGptResponse() {
        viewModel.getGPTResponseLiveData().observe(viewLifecycleOwner, Observer {
            it.let {
                binding.gptResponse.text = it
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
                }
        } catch (e: java.lang.Exception) {
        }
    }



}