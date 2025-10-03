package com.raisac.medmob.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.raisac.medmob.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for authentication operations
 */
interface AuthRepository {
    /**
     * Send verification code to phone number
     */
    suspend fun sendVerificationCode(phoneNumber: String): Resource<String>

    /**
     * Verify phone number with code
     */
    suspend fun verifyPhoneNumber(verificationId: String, code: String): Resource<FirebaseUser>

    /**
     * Resend verification code
     */
    suspend fun resendVerificationCode(
        phoneNumber: String,
        forceResendingToken: Any
    ): Resource<String>

    /**
     * Get current user
     */
    fun getCurrentUser(): FirebaseUser?

    /**
     * Sign out current user
     */
    suspend fun signOut()

    /**
     * Check if user is logged in
     */
    fun isUserLoggedIn(): Boolean

    /**
     * Observe authentication state
     */
    fun observeAuthState(): Flow<FirebaseUser?>
}
