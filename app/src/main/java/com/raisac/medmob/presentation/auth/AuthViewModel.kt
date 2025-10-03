package com.raisac.medmob.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.raisac.medmob.domain.repository.AuthRepository
import com.raisac.medmob.presentation.base.BaseViewModel
import com.raisac.medmob.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for authentication screens
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    private val _verificationState = MutableLiveData<Resource<String>>()
    val verificationState: LiveData<Resource<String>> = _verificationState

    private val _authState = MutableLiveData<Resource<FirebaseUser>>()
    val authState: LiveData<Resource<FirebaseUser>> = _authState

    private val _currentUser = MutableLiveData<FirebaseUser?>()
    val currentUser: LiveData<FirebaseUser?> = _currentUser

    init {
        checkCurrentUser()
        observeAuthState()
    }

    fun sendVerificationCode(phoneNumber: String) {
        viewModelScope.launch {
            _verificationState.value = Resource.Loading()
            val result = authRepository.sendVerificationCode(phoneNumber)
            _verificationState.value = result
        }
    }

    fun verifyPhoneNumber(verificationId: String, code: String) {
        viewModelScope.launch {
            _authState.value = Resource.Loading()
            val result = authRepository.verifyPhoneNumber(verificationId, code)
            _authState.value = result
        }
    }

    fun resendVerificationCode(phoneNumber: String, token: Any) {
        viewModelScope.launch {
            _verificationState.value = Resource.Loading()
            val result = authRepository.resendVerificationCode(phoneNumber, token)
            _verificationState.value = result
        }
    }

    fun signOut() {
        viewModelScope.launch {
            authRepository.signOut()
            _currentUser.value = null
        }
    }

    fun isUserLoggedIn(): Boolean {
        return authRepository.isUserLoggedIn()
    }

    private fun checkCurrentUser() {
        _currentUser.value = authRepository.getCurrentUser()
    }

    private fun observeAuthState() {
        viewModelScope.launch {
            authRepository.observeAuthState().collect { user ->
                _currentUser.value = user
            }
        }
    }
}
