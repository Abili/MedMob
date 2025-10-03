package com.raisac.medmob.data.models

import com.google.gson.annotations.SerializedName

/**
 * Appointment data model for API responses
 */
data class Appointment(
    @SerializedName("id")
    val id: String,
    
    @SerializedName("doctorId")
    val doctorId: String,
    
    @SerializedName("patientId")
    val patientId: String,
    
    @SerializedName("doctorName")
    val doctorName: String? = null,
    
    @SerializedName("patientName")
    val patientName: String? = null,
    
    @SerializedName("appointmentDate")
    val appointmentDate: Long,
    
    @SerializedName("status")
    val status: AppointmentStatus,
    
    @SerializedName("type")
    val type: AppointmentType = AppointmentType.IN_PERSON,
    
    @SerializedName("notes")
    val notes: String? = null,
    
    @SerializedName("location")
    val location: String? = null,
    
    @SerializedName("latitude")
    val latitude: Double? = null,
    
    @SerializedName("longitude")
    val longitude: Double? = null,
    
    @SerializedName("duration")
    val duration: Int = 30, // minutes
    
    @SerializedName("videoCallLink")
    val videoCallLink: String? = null,
    
    @SerializedName("createdAt")
    val createdAt: Long? = null,
    
    @SerializedName("updatedAt")
    val updatedAt: Long? = null
) {
    fun isUpcoming(): Boolean {
        return status == AppointmentStatus.PENDING || status == AppointmentStatus.CONFIRMED
    }
    
    fun isPast(): Boolean {
        return status == AppointmentStatus.COMPLETED || status == AppointmentStatus.CANCELLED
    }
    
    fun canBeCancelled(): Boolean {
        return status == AppointmentStatus.PENDING || status == AppointmentStatus.CONFIRMED
    }
}

/**
 * Appointment status enum
 */
enum class AppointmentStatus {
    @SerializedName("PENDING")
    PENDING,
    
    @SerializedName("CONFIRMED")
    CONFIRMED,
    
    @SerializedName("COMPLETED")
    COMPLETED,
    
    @SerializedName("CANCELLED")
    CANCELLED,
    
    @SerializedName("NO_SHOW")
    NO_SHOW
}

/**
 * Appointment type enum
 */
enum class AppointmentType {
    @SerializedName("IN_PERSON")
    IN_PERSON,
    
    @SerializedName("VIDEO_CALL")
    VIDEO_CALL,
    
    @SerializedName("PHONE_CALL")
    PHONE_CALL,
    
    @SerializedName("HOME_VISIT")
    HOME_VISIT,
    
    @SerializedName("EMERGENCY")
    EMERGENCY
}
