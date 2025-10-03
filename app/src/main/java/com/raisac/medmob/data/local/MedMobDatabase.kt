package com.raisac.medmob.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.raisac.medmob.data.local.dao.DoctorDao
import com.raisac.medmob.data.local.dao.PatientDao
import com.raisac.medmob.data.local.dao.AppointmentDao
import com.raisac.medmob.data.local.entity.DoctorEntity
import com.raisac.medmob.data.local.entity.PatientEntity
import com.raisac.medmob.data.local.entity.AppointmentEntity

/**
 * Room Database for MedMob
 * Provides local storage for offline functionality
 */
@Database(
    entities = [
        DoctorEntity::class,
        PatientEntity::class,
        AppointmentEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MedMobDatabase : RoomDatabase() {
    abstract fun doctorDao(): DoctorDao
    abstract fun patientDao(): PatientDao
    abstract fun appointmentDao(): AppointmentDao
}
