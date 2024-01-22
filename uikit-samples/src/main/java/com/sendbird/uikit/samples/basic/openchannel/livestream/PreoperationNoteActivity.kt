package com.sendbird.uikit.samples.basic.openchannel.livestream

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sendbird.uikit.samples.R
import com.sendbird.uikit.samples.basic.BasicHomeActivity


class PreoperationNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.preoperationnote)


        val patientData = intent.getSerializableExtra("patientData") as BasicHomeActivity.Patient

        // Retrieve data passed from the previous activity
     //   val patientData = intent.getSerializableExtra("patientData") as YourDataType // Replace YourDataType with the actual type of patientData

        // Now you can use patientData as needed in this activity
        // For example, you can update UI elements in the preoperationnote.xml layout
    }
}
