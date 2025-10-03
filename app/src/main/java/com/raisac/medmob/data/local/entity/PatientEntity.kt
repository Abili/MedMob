package com.raisac.medmob.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * Patient entity for Room database
 */
@Entity(tableName = "patients")
data class PatientEntity(
    @PrimaryKey
    val uid: String,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val email: String?,
    val dateOfBirth: String?,
    val address: String?,
    val profilePicUrl: String?,
    val medicalHistory: String?,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)
