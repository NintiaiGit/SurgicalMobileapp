package com.sendbird.uikit.samples.basic.openchannel



import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import com.sendbird.uikit.samples.R
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.client.HttpResponseException



import cz.msebera.android.httpclient.entity.StringEntity
import cz.msebera.android.httpclient.message.BasicHeader
import cz.msebera.android.httpclient.protocol.HTTP


class PatientRegistrationActivity : AppCompatActivity() {

    private val TAG = "PatientREG"

    private  val MAX_REDIRECT_COUNT = 5  // Set your desired maximum number of redirects

    private val SERVERURL = "https://ninti.livemo.io/patientinfo/newreg/"

   // private val SERVERURL = "https://ninti.livemo.io/newreg_test/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_reg)


        val submitButton: Button = findViewById(R.id.submitButton)
        submitButton.setOnClickListener {

            sendDataToServer()
        }
    }



//    private fun sendDataToServer() {
//        // Input validation can be added here
//
//        // Retrieve form data
//        val formData = mapOf(
//            "name" to findViewById<TextInputEditText>(R.id.name).text.toString(),
//            "age" to findViewById<TextInputEditText>(R.id.age).text.toString(),
//            "gender" to findViewById<TextInputEditText>(R.id.gender).text.toString(),
//            "medicalRecordNumber" to findViewById<TextInputEditText>(R.id.medicalRecordNumber).text.toString(),
//            "dateOfSurgery" to findViewById<TextInputEditText>(R.id.dateOfSurgery).text.toString(),
//            "timeOfSurgery" to findViewById<TextInputEditText>(R.id.timeOfSurgery).text.toString(),
//            "procedureDetails" to findViewById<TextInputEditText>(R.id.procedureDetails).text.toString()
//        )
//
//        val client = AsyncHttpClient()
//        val params = RequestParams(formData)
//
//        client.post(SERVERURL, params, object : AsyncHttpResponseHandler() {
//            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
//                // Handle successful response here
//                val response = String(responseBody)
//                Log.d(TAG, "Response from server: $response")
//            }
//
//            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
//                // Handle failure response here
//                Log.e(TAG, "Error: $statusCode", error)
//            }
//        })
//    }


    private fun sendDataToServer() {
        // Retrieve form data
//        val formData = mapOf(
//            "name" to findViewById<TextInputEditText>(R.id.name).text.toString(),
//            "age" to findViewById<TextInputEditText>(R.id.age).text.toString(),
//            "gender" to findViewById<TextInputEditText>(R.id.gender).text.toString(),
//            "medicalRecordNumber" to findViewById<TextInputEditText>(R.id.medicalRecordNumber).text.toString(),
//            "dateOfSurgery" to findViewById<TextInputEditText>(R.id.dateOfSurgery).text.toString(),
//            "timeOfSurgery" to findViewById<TextInputEditText>(R.id.timeOfSurgery).text.toString(),
//            "procedureDetails" to findViewById<TextInputEditText>(R.id.procedureDetails).text.toString()
//        )


        val formData = mapOf(
            "name" to findViewById<TextInputEditText>(R.id.name).text.toString(),
            "age" to findViewById<TextInputEditText>(R.id.age).text.toString(),
            "gender" to findViewById<TextInputEditText>(R.id.gender).text.toString(),
            "medicalRecordNumber" to findViewById<TextInputEditText>(R.id.medicalRecordNumber).text.toString(),
            "dateOfSurgery" to findViewById<TextInputEditText>(R.id.dateOfSurgery).text.toString(),
            "timeOfSurgery" to findViewById<TextInputEditText>(R.id.timeOfSurgery).text.toString(),
            "procedureDetails" to findViewById<TextInputEditText>(R.id.procedureDetails).text.toString()
        )

        val gson = Gson()
        val jsonFormData = gson.toJson(formData)



        val params = RequestParams()
        params.put("json_data", jsonFormData)
        Log.d(TAG, "Params: $jsonFormData")




       // val params = jsonFormData;//RequestParams(formData)

        val client = AsyncHttpClient()

// Create a StringEntity with the JSON data
        val entity = StringEntity(jsonFormData)
        entity.contentType = BasicHeader(HTTP.CONTENT_TYPE, "application/json")

        client.post(this, SERVERURL, entity, "application/json", object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                // Handle successful response here
                val response = String(responseBody)
                Log.d(TAG, "Response from server: $response")
            }

            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                // Handle failure response here
                Log.e(TAG, "Error: $statusCode", error)

                if (statusCode == 307) {
                    // Temporary Redirect
                    val redirectUrl = headers.firstOrNull { it.name.equals("Location", true) }?.value
                    if (!redirectUrl.isNullOrBlank()) {
                        // Make another request to the redirected URL
                        handleTemporaryRedirect(redirectUrl, params)
                    }
                } else if (error is HttpResponseException) {
                    // Handle other HttpResponseException
                    val errorMessage = "HTTP response error: ${error.statusCode} - ${error.localizedMessage}"
                    Log.e(TAG, errorMessage)
                } else {
                    // Handle other types of exceptions
                    Log.e(TAG, "Other error: ${error.localizedMessage}")
                }
            }
        })
    }

    private fun handleTemporaryRedirect(redirectUrl: String, params: RequestParams, redirectCount: Int = 0) {
        Log.d(TAG, "Handling redirect to: $redirectUrl")

        val client = AsyncHttpClient()

        // Make another request to the redirected URL
        client.post(redirectUrl, params, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                // Handle successful response here
                val response = String(responseBody)
                Log.d(TAG, "Response from redirected server: $response")
            }

            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                // Handle failure response here
                Log.e(TAG, "Error in redirected request: $statusCode", error)

                if (statusCode == 307 && redirectCount < MAX_REDIRECT_COUNT) {
                    // Recursive call for another redirect
                    handleTemporaryRedirect(headers.firstOrNull { it.name.equals("Location", true) }?.value ?: "", params, redirectCount + 1)
                }
            }
        })
    }




//    private fun sendDataToServer() {
//        // Your server endpoint URL
//        val myUrl = SERVER_URL
//
//        // Create a JSONObject with the form data
//        val formData = JSONObject().apply {
//            put("name", findViewById<TextInputEditText>(R.id.name).text.toString())
//            put("age", findViewById<TextInputEditText>(R.id.age).text.toString())
//            put("gender", findViewById<TextInputEditText>(R.id.gender).text.toString())
//            put("medicalRecordNumber", findViewById<TextInputEditText>(R.id.medicalRecordNumber).text.toString())
//            put("dateOfSurgery", findViewById<TextInputEditText>(R.id.dateOfSurgery).text.toString())
//            put("timeOfSurgery", findViewById<TextInputEditText>(R.id.timeOfSurgery).text.toString())
//            put("procedureDetails", findViewById<TextInputEditText>(R.id.procedureDetails).text.toString())
//        }
//
//        Log.d("RequestData", "Sending request with data: $formData")
//
//        // Create a JsonObjectRequest
//        val request = object : JsonObjectRequest(Method.POST, myUrl, formData,
//            Response.Listener {
//                // Handle successful response here
//                Log.d("RequestResponse", "Successful response:")
//               // Toast.makeText(this, "My_token=${it.getString("token")}", Toast.LENGTH_SHORT).show()
//            },
//            Response.ErrorListener {
//                // Handle failure response here
//                Log.e("RequestResponse", "Error:")
//                // Toast.makeText(this, "Error=${it.message}", Toast.LENGTH_SHORT).show()
//            }
//        ) {
//            // Optional: Override the getHeaders method if you need to add headers to your request
//
//            // Optional: Override the getParams method if you need to add query parameters to your request
//
//            // Optional: Override the getBodyContentType method if you need a custom content type
//            override fun getBodyContentType(): String {
//                return "application/json; charset=utf-8"
//            }
//        }
//
//        // Add the request to the request queue
//        // MySingleton.getInstance(this).addToRequestQueue(request)
//    }








}