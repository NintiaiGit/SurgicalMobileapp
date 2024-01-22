package com.sendbird.uikit.samples.basic.openchannel

//import kotlinx.android.synthetic.main.activity_patient_home.*  // Import this line if you are using View Binding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sendbird.uikit.samples.R
import com.sendbird.uikit.samples.basic.BasicHomeActivity.Patient
import com.sendbird.uikit.samples.basic.openchannel.livestream.PreoperationNoteActivity

import com.sendbird.uikit.samples.basic.openchannel.livestream.operationnote


import com.sendbird.uikit.samples.common.widgets.EntryButton




class PatientHome : AppCompatActivity() {

    private val TAG = "PatinetAdapter"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.patient_home)

        // Retrieve patient data from intent extras
        val patientData = intent.getSerializableExtra("patientData") as Patient

        // Display patient information
        val patientInfoTextView = findViewById<TextView>(R.id.patientInfoTextView)
        val patientInfoText = "Name: ${patientData.name}\n" +
            "Medical Record Number: ${patientData.medicalRecordNumber}\n" +
            "Date of Surgery: ${patientData.dateOfSurgery}\n" +
            "Time of Surgery: ${patientData.timeOfSurgery}\n" +
            "Procedure Details: ${patientData.procedureDetails}"

        patientInfoTextView.text = patientInfoText

        val preoperationnoteButton = findViewById<EntryButton>(R.id.preoperationnote)
        preoperationnoteButton.setOnClickListener {
            // Navigate to preoperationnote.xml

            Log.e(TAG, "Patient hone - preoperative note")



            val intent = Intent(this, PreoperationNoteActivity::class.java)
            // Pass necessary data to the next activity if needed
            intent.putExtra("patientData", patientData)
            startActivity(intent)
        }


        val operationnoteButton = findViewById<EntryButton>(R.id.operationnote)
        preoperationnoteButton.setOnClickListener {


            Log.e(TAG, "Patient hone - operation note")



            val intent = Intent(this, operationnote::class.java)
            // Pass necessary data to the next activity if needed
            intent.putExtra("patientData", patientData)
            startActivity(intent)
        }






        val intraoperationfindingnoteButton = findViewById<EntryButton>(R.id.operationnote)
        preoperationnoteButton.setOnClickListener {


            Log.e(TAG, "Patient hone - preoperative note")



            val intent = Intent(this, PreoperationNoteActivity::class.java)
            // Pass necessary data to the next activity if needed
            intent.putExtra("patientData", patientData)
            startActivity(intent)
        }











    }






    // ... rest of the code
}
