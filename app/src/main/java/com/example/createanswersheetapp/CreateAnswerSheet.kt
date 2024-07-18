package com.example.createanswersheetapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.createanswersheetapp.databinding.FragmentCreateAnswerSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CreateAnswerSheet : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentCreateAnswerSheetBinding
    private lateinit var answerSheetViewModel: AnswerSheetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = requireActivity()
        answerSheetViewModel = ViewModelProvider(activity).get(AnswerSheetViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCreateAnswerSheetBinding.inflate(inflater, container, false)

        binding.buttonCreateSheet.setOnClickListener {
            saveSheetData()
        }
        return binding.root
    }

    private fun saveSheetData(){
        val sheetName = binding.editTextSheetName.text.toString()
        val numberOfItems = binding.editTextNumberOfItems.text.toString().toIntOrNull() ?: 0
        val hasMultipleChoice = binding.checkBoxMultipleChoice.isChecked
        val hasIdentification = binding.checkBoxIdentification.isChecked
        val hasWordProblem = binding.checkBoxWordProblem.isChecked

        answerSheetViewModel.saveSheetData(sheetName, numberOfItems, hasMultipleChoice, hasIdentification, hasWordProblem)

        clearInputFields()

        dismiss()
    }

    private fun clearInputFields(){
        binding.editTextSheetName.setText("")
        binding.editTextNumberOfItems.setText("")
        binding.checkBoxMultipleChoice.isChecked = false
        binding.checkBoxIdentification.isChecked = false
        binding.checkBoxWordProblem.isChecked = false
    }
}
