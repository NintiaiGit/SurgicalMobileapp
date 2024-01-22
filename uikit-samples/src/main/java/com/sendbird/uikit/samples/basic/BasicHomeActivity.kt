package com.sendbird.uikit.samples.basic

//import com.sendbird.uikit.utils.ContextUtils

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.TextHttpResponseHandler
import com.sendbird.android.SendbirdChat
import com.sendbird.android.exception.SendbirdException
import com.sendbird.android.handler.UnreadMessageCountHandler
import com.sendbird.android.handler.UserEventHandler
import com.sendbird.android.params.GroupChannelTotalUnreadMessageCountParams
import com.sendbird.android.user.UnreadMessageCount
import com.sendbird.android.user.User
import com.sendbird.uikit.samples.basic.openchannel.PatientHome
import com.sendbird.uikit.samples.basic.openchannel.PatientRegistrationActivity
import com.sendbird.uikit.samples.common.ThemeHomeActivity
import com.sendbird.uikit.samples.databinding.ActivityHomeBinding
import cz.msebera.android.httpclient.Header
import java.io.Serializable


class BasicHomeActivity : ThemeHomeActivity(), PatientClickListener {
    override val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { }
    private val appSettingLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { }

    override fun onPatientClick(patient: Patient) {

        Log.e(TAG, "Onclick triggered")
        // Handle the click event, for example, redirect to PatientHome.kt with patient data
        val intent = Intent(this, PatientHome::class.java)
        // Pass the patient data to the next activity using intent extras
        intent.putExtra("patientData", patient)
        startActivity(intent)
    }


    private val TAG = "BasicActivity"

    data class Patient(
        val name: String,
        val age: Int, // Replace with the actual data type
        val gender: String,
        val medicalRecordNumber: String,
        val dateOfSurgery: String,
        val timeOfSurgery: String,
        val procedureDetails: String
    ) : Serializable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {



            binding.btPatientregister.setOnClickListener {
                // Redirect to patient registration page
                startActivity(Intent(this@BasicHomeActivity, PatientRegistrationActivity::class.java))
            }




            val recyclerView = patientRecyclerView
            recyclerView.layoutManager = LinearLayoutManager(this@BasicHomeActivity)

            // Retrieve and display patient data
            retrievePatientData { patients ->
                recyclerView.adapter = PatientAdapter(this@BasicHomeActivity, patients.toList())
            }


        }


       // retrievePatientData();
    }



    //Retrive patient data on load -- date should be added later
    private fun retrievePatientData(onSuccess: (Array<Patient>) -> Unit) {
        val client = AsyncHttpClient()

        client.get("https://ninti.livemo.io/patientinfo/fetchall_patients/", object : TextHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseString: String) {
                Log.d(TAG, "Response from server: $responseString")

                // Parse the JSON response using Gson
                val patients = Gson().fromJson(responseString, Array<Patient>::class.java)

                // Callback with the list of patients
                onSuccess(patients)
            }

            override fun onFailure(statusCode: Int, headers: Array<Header>, responseString: String, throwable: Throwable) {
                Log.e(TAG, "Error: $statusCode", throwable)
                // Handle failure response here
            }
        })
    }


    override fun applyTheme() {
        super.applyTheme()
//        binding.mainTitle.setTextColorResource(if (isDarkTheme) R.color.ondark_01 else R.color.onlight_01)
//        binding.btSignOut.setBackgroundResource(
//            if (isDarkTheme) R.drawable.selector_home_signout_button_dark
//            else R.drawable.selector_home_signout_button
//        )
    }

    override fun onResume() {
        super.onResume()
        // initialize total unread count
        SendbirdChat.getTotalUnreadMessageCount(
            GroupChannelTotalUnreadMessageCountParams(),
            UnreadMessageCountHandler { totalCount: Int, _: Int, e: SendbirdException? ->
                if (e != null) {
                    return@UnreadMessageCountHandler
                }
//                if (totalCount > 0) {
//                    binding.groupChannelButton.unreadCountVisibility = View.VISIBLE
//                    binding.groupChannelButton.unreadCount =
//                        if (totalCount > 99) getString(R.string.text_tab_badge_max_count) else totalCount.toString()
//                } else {
//                    binding.groupChannelButton.unreadCountVisibility = View.GONE
//                }
            })
        // register total unread count event
        SendbirdChat.addUserEventHandler(USER_EVENT_HANDLER_KEY, object : UserEventHandler() {
            override fun onFriendsDiscovered(users: List<User>) {}
            override fun onTotalUnreadMessageCountChanged(unreadMessageCount: UnreadMessageCount) {
                val totalCount = unreadMessageCount.groupChannelCount
//                if (totalCount > 0) {
//                    binding.groupChannelButton.unreadCountVisibility = View.VISIBLE
//                    binding.groupChannelButton.unreadCount =
//                        if (totalCount > 99) getString(R.string.text_tab_badge_max_count) else totalCount.toString()
//                } else {
//                    binding.groupChannelButton.unreadCountVisibility = View.GONE
//                }
            }
        })
    }

    override fun onPause() {
        super.onPause()
        SendbirdChat.removeUserEventHandler(USER_EVENT_HANDLER_KEY)
    }

    private fun showPermissionRationalePopup() {
//        val builder = AlertDialog.Builder(this)
//        builder.setTitle(getString(com.sendbird.uikit.R.string.sb_text_dialog_permission_title))
//        builder.setMessage(
//            String.format(
//                Locale.US,
//                getString(R.string.sb_text_need_to_allow_permission_notification),
//                ContextUtils.getApplicationName(this)
//            )
//        )
//        builder.setPositiveButton(com.sendbird.uikit.R.string.sb_text_go_to_settings) { _: DialogInterface?, _: Int ->
//            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
//            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
//            intent.addCategory(Intent.CATEGORY_DEFAULT)
//            intent.data = Uri.parse("package:$packageName")
//            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
//            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
//            appSettingLauncher.launch(intent)
//        }
//        val dialog = builder.create()
//        dialog.show()
//        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
//            .setTextColor(ContextCompat.getColor(this, com.sendbird.uikit.R.color.secondary_300))
    }

    companion object {
        private val USER_EVENT_HANDLER_KEY = "USER_EVENT_HANDLER_KEY" + System.currentTimeMillis()
    }



}
