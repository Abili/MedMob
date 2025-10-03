package com.raisac.medmob.data.local.dao

import androidx.room.*
import com.raisac.medmob.data.local.entity.PatientEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for Patient entities
 */
@Dao
interface PatientDao {
    @Query("SELECT * FROM patients")
    fun getAllPatients(): Flow<List<PatientEntity>>

    @Query("SELECT * FROM patients WHERE uid = :uid")
    fun getPatientById(uid: String): Flow<PatientEntity?>

    @Query("SELECT * FROM patients WHERE firstName LIKE '%' || :query || '%' OR lastName LIKE '%' || :query || '%'")
    fun searchPatients(query: String): Flow<List<PatientEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPatient(patient: PatientEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPatients(patients: List<PatientEntity>)

    @Update
    suspend fun updatePatient(patient: PatientEntity)

    @Delete
    suspend fun deletePatient(patient: PatientEntity)

    @Query("DELETE FROM patients WHERE uid = :uid")
    suspend fun deletePatientById(uid: String)
}
