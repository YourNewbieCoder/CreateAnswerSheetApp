package com.example.createanswersheetapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.createanswersheetapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var answerSheetViewModel: AnswerSheetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        answerSheetViewModel = ViewModelProvider(this).get(AnswerSheetViewModel::class.java)

        binding.createSheetBtn.setOnClickListener{
            CreateAnswerSheet().show(supportFragmentManager, "createSheet")
        }

        answerSheetViewModel.sheetData.observe(this, Observer { sheet ->
            Toast.makeText(this, "Sheet Created: ${sheet.sheetName}", Toast.LENGTH_SHORT).show()

            binding.createdSheetName.text = sheet.sheetName
        })
    }
}