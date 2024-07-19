package com.example.createanswersheetapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.createanswersheetapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var answerSheetViewModel: AnswerSheetViewModel
    private lateinit var adapter: AnswerSheetAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        answerSheetViewModel = ViewModelProvider(this).get(AnswerSheetViewModel::class.java)

        binding.createSheetBtn.setOnClickListener{
            CreateAnswerSheet().show(supportFragmentManager, "createSheet")
        }

        adapter = AnswerSheetAdapter(listOf(), this::editSheet, this::deleteSheet)
        binding.recycleViewSheets.layoutManager = LinearLayoutManager(this)
        binding.recycleViewSheets.adapter = adapter

        answerSheetViewModel.sheetDataList.observe(this, Observer { sheets ->
            adapter = AnswerSheetAdapter(sheets, this::editSheet, this::deleteSheet)
            binding.recycleViewSheets.adapter = adapter
        })
    }

    private fun editSheet(sheet: AnswerSheet){
        CreateAnswerSheet(sheet).show(supportFragmentManager, "editSheet")
    }

    private fun deleteSheet(sheet: AnswerSheet) {
        answerSheetViewModel.deleteSheet(sheet.id)
    }
}