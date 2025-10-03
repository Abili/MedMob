package com.raisac.medmob.presentation.auth

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.raisac.medmob.Auths.UserProfile
import com.raisac.medmob.R
import com.raisac.medmob.databinding.ActivityMainBinding
import com.raisac.medmob.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

/**
 * Modern Kotlin version of MainActivity with MVVM pattern
 * Handles phone authentication with Firebase
 */
@AndroidEntryPoint
class MainActivityKt : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: AuthViewModel by viewModels()

    private var verificationId: String? = null
    private var resendToken: PhoneAuthProvider.ForceResendingToken? = null

    private val phoneAuthCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // Auto-retrieval of SMS code
            credential.smsCode?.let { code ->
                binding.verificationCode.setText(code)
                verificationId?.let { id ->
                    viewModel.verifyPhoneNumber(id, code)
                }
            }
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Toast.makeText(this@MainActivityKt, e.message, Toast.LENGTH_LONG).show()
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            super.onCodeSent(verificationId, token)
            this@MainActivityKt.verificationId = verificationId
            this@MainActivityKt.resendToken = token
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupObservers()
    }

    private fun setupUI() {
        binding.apply {
            buttonResend.isEnabled = false
            buttonResend.setTextColor(Color.GRAY)

            doctorSignUp.setOnClickListener {
                handleSignUp()
            }

            doctorVerify.setOnClickListener {
                handleVerify()
            }

            buttonResend.setOnClickListener {
                handleResend()
            }
        }
    }

    private fun setupObservers() {
        viewModel.verificationState.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    // Show loading state if needed
                }
                is Resource.Success -> {
                    Toast.makeText(this, "Verification code sent", Toast.LENGTH_SHORT).show()
                }
                is Resource.Error -> {
                    Toast.makeText(this, resource.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        viewModel.authState.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    // Show loading state
                }
                is Resource.Success -> {
                    navigateToProfile()
                }
                is Resource.Error -> {
                    Toast.makeText(this, resource.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun handleSignUp() {
        val phoneNumber = binding.phoneNumber.text.toString()
        
        if (phoneNumber.startsWith("+256") && phoneNumber.length == 13) {
            binding.phoneNumber.isEnabled = false
            binding.doctorVerify.visibility = View.VISIBLE
            binding.doctorSignUp.visibility = View.INVISIBLE
            
            sendVerificationCode(phoneNumber)
            
            binding.buttonResend.isEnabled = true
            binding.buttonResend.setTextColor(Color.BLUE)
        } else {
            binding.phoneNumber.error = "Enter Valid phone Number"
        }
    }

    private fun handleVerify() {
        val code = binding.verificationCode.text.toString().trim()
        
        if (code.isEmpty() || code.length < 6) {
            binding.verificationCode.error = "Enter valid code"
            binding.verificationCode.requestFocus()
            return
        }

        verificationId?.let { id ->
            viewModel.verifyPhoneNumber(id, code)
        }
    }

    private fun handleResend() {
        val phoneNumber = binding.phoneNumber.text.toString()
        resendToken?.let { token ->
            resendVerificationCode(phoneNumber, token)
        }
    }

    private fun sendVerificationCode(phoneNumber: String) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber,
            60,
            TimeUnit.SECONDS,
            TaskExecutors.MAIN_THREAD,
            phoneAuthCallbacks
        )
    }

    private fun resendVerificationCode(
        phoneNumber: String,
        token: PhoneAuthProvider.ForceResendingToken
    ) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber,
            60,
            TimeUnit.SECONDS,
            this,
            phoneAuthCallbacks,
            token
        )
    }

    private fun navigateToProfile() {
        val intent = Intent(this, UserProfile::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        finish()
    }
}
