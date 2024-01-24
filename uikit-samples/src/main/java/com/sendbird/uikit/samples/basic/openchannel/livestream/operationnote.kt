package com.sendbird.uikit.samples.basic.openchannel.livestream


import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.sendbird.uikit.samples.R
import com.sendbird.uikit.samples.basic.BasicHomeActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

data class OperationData(
    val patientname: String,
    val medical_record_number: String,
    val audio_filepath: String,
    val audio_text_cpp: String,
    val operationdata_bit: String
)

interface ApiService {
    @GET("/procedure/audio_text_cpp/{note_id}")
    suspend fun getOperationData(@Path("note_id") noteId: String): OperationData
}




class operationnote  : AppCompatActivity() {

    private val TAG = "Operation Notes"
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.operationnote)

        val patientData = intent.getSerializableExtra("patientData") as BasicHomeActivity.Patient

        Log.e(TAG, "QR code start")

        val name = patientData.name
        val regNumber = patientData.medicalRecordNumber
        val combinedData = "$name, $regNumber"

        // Generate QR code
        val qrCodeImageView = findViewById<ImageView>(R.id.qrCodeImageView)
        generateQRCode(combinedData, qrCodeImageView)

        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://ninti.livemo.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        // Fetch data from the server
       fetchDataFromServer(patientData.medicalRecordNumber)

      //  fetchDataFromServer("123456")
    }

    private fun generateQRCode(data: String, imageView: ImageView) {
        val multiFormatWriter = MultiFormatWriter()
        try {
            Log.e(TAG, "QR generation")
            val bitMatrix = multiFormatWriter.encode(data, BarcodeFormat.QR_CODE, 500, 500)
            val barcodeEncoder = BarcodeEncoder()
            val bitmap: Bitmap = barcodeEncoder.createBitmap(bitMatrix)
            imageView.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }

    private fun fetchDataFromServer(noteId: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val operationData = apiService.getOperationData(noteId)

                // Update the UI with the retrieved data
                withContext(Dispatchers.Main) {
                    updateUI(operationData)
                }
            } catch (e: Exception) {
                // Handle exception
                Log.e(TAG, "Error during API request", e)
            }
        }
    }

    private fun updateUI(operationData: OperationData) {
        // Update the UI elements with the retrieved data
        val documentTextView = findViewById<TextView>(R.id.documentTextView)
        documentTextView.text = operationData.audio_text_cpp
    }





}