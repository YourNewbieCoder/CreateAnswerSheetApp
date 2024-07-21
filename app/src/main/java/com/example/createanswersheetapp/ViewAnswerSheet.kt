package com.example.createanswersheetapp

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.createanswersheetapp.databinding.FragmentViewAnswerSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ViewAnswerSheet(private val sheet: AnswerSheet) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentViewAnswerSheetBinding
    private val PERMISSION_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentViewAnswerSheetBinding.inflate(inflater, container, false)

        binding.textViewSheetName.text = sheet.sheetName
        binding.textViewNumberOfItems.text = sheet.numberOfItems.toString()
        binding.hasMultipleChoice.isChecked = sheet.hasMultipleChoice
        binding.hasIdentification.isChecked = sheet.hasIdentification
        binding.hasWordProblem.isChecked = sheet.hasWordProblem

        binding.hasMultipleChoice.isEnabled = false
        binding.hasIdentification.isEnabled = false
        binding.hasWordProblem.isEnabled = false

        binding.buttonDownloadPdf.setOnClickListener{
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)
            } else{
                downloadPdf()
            }
        }

        return binding.root
    }

    private fun downloadPdf(){
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(binding.root.width, binding.root.height, 1).create()
        val page = pdfDocument.startPage(pageInfo)

        binding.root.draw(page.canvas)
        pdfDocument.finishPage(page)

        val filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/${sheet.sheetName}.pdf"
        val file = File(filePath)
        try {
            pdfDocument.writeTo(FileOutputStream(file))
            Toast.makeText(requireContext(), "PDF downloaded to $filePath", Toast.LENGTH_LONG).show()
        } catch (e: IOException){
            Toast.makeText(requireContext(), "Error downloading PDF: ${e.message}", Toast.LENGTH_LONG).show()
        } finally {
            pdfDocument.close()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                downloadPdf()
            } else{
                Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_LONG).show()
            }
        }
    }
}