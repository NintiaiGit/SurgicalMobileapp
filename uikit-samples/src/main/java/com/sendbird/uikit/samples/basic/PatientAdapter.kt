package com.sendbird.uikit.samples.basic

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sendbird.uikit.samples.R
import com.sendbird.uikit.samples.basic.BasicHomeActivity.Patient
import com.sendbird.uikit.samples.common.widgets.EntryButton



interface PatientClickListener {

    fun onPatientClick(patient: Patient)
}
class PatientAdapter(private val patientClickListener: PatientClickListener, private val patients: List<Patient>) : RecyclerView.Adapter<PatientAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val submitLabelTextView: TextView = itemView.findViewById(R.id.submitLabelTextView)
        val patientEntryButton: EntryButton = itemView.findViewById(R.id.patientEntryButton)




    }

    private val TAG = "PatinetAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_patient, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val patient = patients[position]
        holder.nameTextView.text = patient.name + ":" + patient.age + ".RegNo : " + patient.medicalRecordNumber

//        holder.submitLabelTextView.setOnClickListener {
//            // Trigger the click event with the clicked patient
//
//            Log.e(TAG, "Onclick triggered33")
//            patientClickListener.onPatientClick(patient)
//        }


        holder.patientEntryButton.setOnClickListener {
            // Trigger the click event with the clicked patient
            Log.e(TAG, "patientEntryButton clicked")
            patientClickListener.onPatientClick(patient)
        }
    }

    override fun getItemCount(): Int {
        return patients.size
    }
}
