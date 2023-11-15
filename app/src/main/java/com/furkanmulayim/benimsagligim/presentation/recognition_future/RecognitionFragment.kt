package com.furkanmulayim.benimsagligim.presentation.recognition_future

import android.app.ProgressDialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.databinding.FragmentRecognitionBinding
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

class RecognitionFragment : Fragment() {

    private lateinit var binding: FragmentRecognitionBinding
    private lateinit var viewModel: RecognitionViewModel
    private lateinit var proggresDialog: ProgressDialog
    private lateinit var textRecognizer: TextRecognizer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recognition, container, false)
        viewModel = ViewModelProvider(this)[RecognitionViewModel::class.java]

        proggresDialog = ProgressDialog(requireContext())
        proggresDialog.setTitle("Lütfen Bekleyin.. !")
        proggresDialog.setCanceledOnTouchOutside(false)

        textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recognizeTextFromImage(viewModel.parsUrl())
    }


    private fun recognizeTextFromImage(resim: Uri) {
        proggresDialog.setMessage("Görsel Hazırlanıyor.. !")
        proggresDialog.show()
        try {
            val inputImage = resim.let { InputImage.fromFilePath(requireContext(), it) }
            proggresDialog.setMessage(" Görüntü Tanımlanıyor.. !")
            val textTaskResult =
                inputImage.let { textRecognizer.process(it) }.addOnSuccessListener { _text ->
                    //binding.recognizedTextEt.setText(text.text)
                    println("VERİ: "+ _text.text)
                    withBundleForNavTransaction(_text.text)
                    proggresDialog.dismiss()
                }.addOnFailureListener { ex ->
                    proggresDialog.dismiss()
                    //showToast("Lütfen Tekrar Deneyin.. !")
                }
        } catch (e: java.lang.Exception) {
            proggresDialog.dismiss()
            //showToast("Bir Sorun Çıktı.. !")
        }
    }

    private fun withBundleForNavTransaction(text: String) {
        // bundle ile aksiyonumuzu tanımlayarak geçiş fonksiyonuna iletiyoruz
        val act = RecognitionFragmentDirections.actionRecognitionFragmentToSearchFragment(text)
        viewModel.navigate(requireView(), act)

    }


}