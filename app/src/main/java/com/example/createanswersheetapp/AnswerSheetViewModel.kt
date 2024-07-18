package com.example.createanswersheetapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class AnswerSheet(
    val sheetName: String,
    val numberOfItems: Int,
    val hasMultipleChoice: Boolean,
    val hasIdentification: Boolean,
    val hasWordProblem: Boolean
)

class AnswerSheetViewModel: ViewModel() {
    private val _sheetData = MutableLiveData<AnswerSheet>()
    val sheetData: LiveData<AnswerSheet> get() = _sheetData

    fun saveSheetData(sheetName: String, numberOfItems: Int, hasMultipleChoice: Boolean, hasIdentification: Boolean, hasWordProblem: Boolean){
        _sheetData.value = AnswerSheet(sheetName, numberOfItems, hasMultipleChoice, hasIdentification, hasWordProblem)
    }

    fun clearSheetData() {
        _sheetData.value = AnswerSheet("", 0, false, false, false)
    }
}