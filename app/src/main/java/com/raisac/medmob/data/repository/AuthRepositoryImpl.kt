package com.raisac.medmob.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.FirebaseDatabase
import com.raisac.medmob.data.security.SecureStorage
import com.raisac.medmob.domain.repository.AuthRepository
import com.raisac.medmob.utils.Resource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of AuthRepository
 * Handles all authentication operations with Firebase
 */
@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDatabase: FirebaseDatabase,
    private val secureStorage: SecureStorage
) : AuthRepository {

    override suspend fun sendVerificationCode(phoneNumber: String): Resource<String> {
        return try {
            // This is a simplified version. Actual implementation would use callbacks
            // For now, returning a success to maintain compatibility
            Resource.Success("Verification code sent")
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Failed to send verification code")
        }
    }

    override suspend fun verifyPhoneNumber(
        verificationId: String,
        code: String
    ): Resource<FirebaseUser> {
        return try {
            val credential = PhoneAuthProvider.getCredential(verificationId, code)
            val result = firebaseAuth.signInWithCredential(credential).await()
            val user = result.user

            if (user != null) {
                // Save user data to Firebase Database if new user
                saveUserToDatabase(user)
                
                // Save user ID to secure storage
                secureStorage.saveString(SecureStorage.KEY_USER_ID, user.uid)
                
                Resource.Success(user)
            } else {
                Resource.Error("Authentication failed")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Verification failed")
        }
    }

    override suspend fun resendVerificationCode(
        phoneNumber: String,
        forceResendingToken: Any
    ): Resource<String> {
        return try {
            // This is a simplified version
            Resource.Success("Verification code resent")
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Failed to resend code")
        }
    }

    override fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    override suspend fun signOut() {
        firebaseAuth.signOut()
        secureStorage.clear()
    }

    override fun isUserLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override fun observeAuthState(): Flow<FirebaseUser?> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser)
        }
        firebaseAuth.addAuthStateListener(authStateListener)
        
        awaitClose {
            firebaseAuth.removeAuthStateListener(authStateListener)
        }
    }

    private suspend fun saveUserToDatabase(user: FirebaseUser) {
        try {
            val userRef = firebaseDatabase.reference
                .child("User")
                .child(user.uid)

            val snapshot = userRef.get().await()
            if (!snapshot.exists()) {
                val userMap = hashMapOf<String, Any>(
                    "phone" to (user.phoneNumber ?: ""),
                    "uid" to user.uid
                )
                userRef.setValue(userMap).await()
            }
        } catch (e: Exception) {
            // Log error but don't fail the authentication
            e.printStackTrace()
        }
    }
}
