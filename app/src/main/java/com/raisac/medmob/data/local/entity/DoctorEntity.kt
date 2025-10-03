package com.raisac.medmob.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * Doctor entity for Room database
 */
@Entity(tableName = "doctors")
data class DoctorEntity(
    @PrimaryKey
    val uid: String,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val email: String?,
    val department: String?,
    val experience: String?,
    val dateOfBirth: String?,
    val profilePicUrl: String?,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)
