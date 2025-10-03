package com.raisac.medmob.data.models

import com.google.gson.annotations.SerializedName

/**
 * Patient data model for API responses
 */
data class Patient(
    @SerializedName("uid")
    val uid: String,
    
    @SerializedName("firstName")
    val firstName: String,
    
    @SerializedName("lastName")
    val lastName: String,
    
    @SerializedName("phone")
    val phone: String,
    
    @SerializedName("email")
    val email: String? = null,
    
    @SerializedName("dateOfBirth")
    val dateOfBirth: String? = null,
    
    @SerializedName("gender")
    val gender: String? = null,
    
    @SerializedName("bloodGroup")
    val bloodGroup: String? = null,
    
    @SerializedName("address")
    val address: String? = null,
    
    @SerializedName("emergencyContact")
    val emergencyContact: String? = null,
    
    @SerializedName("profilePicUrl")
    val profilePicUrl: String? = null,
    
    @SerializedName("medicalHistory")
    val medicalHistory: String? = null,
    
    @SerializedName("allergies")
    val allergies: List<String>? = null,
    
    @SerializedName("chronicConditions")
    val chronicConditions: List<String>? = null,
    
    @SerializedName("currentMedications")
    val currentMedications: List<String>? = null,
    
    @SerializedName("createdAt")
    val createdAt: Long? = null,
    
    @SerializedName("updatedAt")
    val updatedAt: Long? = null
) {
    fun getFullName(): String = "$firstName $lastName"
    
    fun hasAllergies(): Boolean = !allergies.isNullOrEmpty()
    
    fun hasChronicConditions(): Boolean = !chronicConditions.isNullOrEmpty()
    
    fun getAge(): Int? {
        // Calculate age from dateOfBirth if available
        // Implementation depends on date format
        return null
    }
}
