package com.example.createanswersheetapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class AnswerSheet(
    val id: Int,
    val sheetName: String,
    val numberOfItems: Int,
    val hasMultipleChoice: Boolean,
    val hasIdentification: Boolean,
    val hasWordProblem: Boolean
)

class AnswerSheetViewModel: ViewModel() {
    private val _sheetDataList = MutableLiveData<List<AnswerSheet>>()
    val sheetDataList: LiveData<List<AnswerSheet>> get() = _sheetDataList

    private var nextId = 0

    init {
        _sheetDataList.value = mutableListOf()
    }

    fun createSheet(sheetName: String, numberOfItems: Int, hasMultipleChoice: Boolean, hasIdentification: Boolean, hasWordProblem: Boolean){
        val newSheet = AnswerSheet(nextId++, sheetName, numberOfItems, hasMultipleChoice, hasIdentification, hasWordProblem)
        val updatedList = _sheetDataList.value?.toMutableList()
        updatedList?.add(newSheet)
        _sheetDataList.value = updatedList
    }

    fun updateSheet(id: Int, sheetName: String, numberOfItems: Int, hasMultipleChoice: Boolean, hasIdentification: Boolean, hasWordProblem: Boolean){
        val updatedList = sheetDataList.value?.map {
            if (it.id == id){
                AnswerSheet(id, sheetName, numberOfItems, hasMultipleChoice, hasIdentification, hasWordProblem)
            } else {
                it
            }
        }
        _sheetDataList.value = updatedList
    }

    fun deleteSheet(id: Int){
        val updatedList = _sheetDataList.value?.filterNot { it.id == id }
        _sheetDataList.value = updatedList
    }
}