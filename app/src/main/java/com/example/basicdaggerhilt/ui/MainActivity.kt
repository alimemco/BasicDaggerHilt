package com.example.basicdaggerhilt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import com.example.basicdaggerhilt.R
import com.example.basicdaggerhilt.repository.api.RequestStatus
import com.example.basicdaggerhilt.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val userViewModel : UserViewModel  by viewModels()

    private val textView by lazy {
        findViewById<TextView>(R.id.textView_status)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeUser()

    }

    private fun observeUser() {
        userViewModel.users.observe(this , { resource ->

            Log.i(TAG, "request status : ${resource.requestStatus}")

            when (resource.requestStatus){
                RequestStatus.LOADING -> textView.text = "Loading"
                RequestStatus.SUCCESS -> textView.text = "Success"
                RequestStatus.ERROR -> textView.text = "Error"
            }

        })
    }

    companion object {
        private const val TAG = "LOG_ME"
    }
}