package com.raisac.medmob.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * Appointment entity for Room database
 */
@Entity(tableName = "appointments")
data class AppointmentEntity(
    @PrimaryKey
    val id: String,
    val doctorId: String,
    val patientId: String,
    val appointmentDate: Date,
    val status: String, // PENDING, CONFIRMED, COMPLETED, CANCELLED
    val notes: String?,
    val location: String?,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)
