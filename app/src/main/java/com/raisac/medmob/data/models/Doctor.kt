package com.raisac.medmob.data.models

import com.google.gson.annotations.SerializedName

/**
 * Doctor data model for API responses
 */
data class Doctor(
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
    
    @SerializedName("department")
    val department: String? = null,
    
    @SerializedName("experience")
    val experience: String? = null,
    
    @SerializedName("dateOfBirth")
    val dateOfBirth: String? = null,
    
    @SerializedName("profilePicUrl")
    val profilePicUrl: String? = null,
    
    @SerializedName("isAvailable")
    val isAvailable: Boolean = false,
    
    @SerializedName("rating")
    val rating: Double = 0.0,
    
    @SerializedName("specialization")
    val specialization: String? = null,
    
    @SerializedName("licenseNumber")
    val licenseNumber: String? = null,
    
    @SerializedName("createdAt")
    val createdAt: Long? = null,
    
    @SerializedName("updatedAt")
    val updatedAt: Long? = null
) {
    fun getFullName(): String = "$firstName $lastName"
    
    fun getDisplayExperience(): String = experience ?: "Not specified"
    
    fun getDisplayDepartment(): String = department ?: "General Practice"
}
