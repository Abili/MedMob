package com.raisac.medmob.data.local.dao

import androidx.room.*
import com.raisac.medmob.data.local.entity.AppointmentEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for Appointment entities
 */
@Dao
interface AppointmentDao {
    @Query("SELECT * FROM appointments ORDER BY appointmentDate DESC")
    fun getAllAppointments(): Flow<List<AppointmentEntity>>

    @Query("SELECT * FROM appointments WHERE id = :id")
    fun getAppointmentById(id: String): Flow<AppointmentEntity?>

    @Query("SELECT * FROM appointments WHERE doctorId = :doctorId ORDER BY appointmentDate DESC")
    fun getAppointmentsByDoctor(doctorId: String): Flow<List<AppointmentEntity>>

    @Query("SELECT * FROM appointments WHERE patientId = :patientId ORDER BY appointmentDate DESC")
    fun getAppointmentsByPatient(patientId: String): Flow<List<AppointmentEntity>>

    @Query("SELECT * FROM appointments WHERE status = :status ORDER BY appointmentDate DESC")
    fun getAppointmentsByStatus(status: String): Flow<List<AppointmentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppointment(appointment: AppointmentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppointments(appointments: List<AppointmentEntity>)

    @Update
    suspend fun updateAppointment(appointment: AppointmentEntity)

    @Delete
    suspend fun deleteAppointment(appointment: AppointmentEntity)

    @Query("DELETE FROM appointments WHERE id = :id")
    suspend fun deleteAppointmentById(id: String)
}
