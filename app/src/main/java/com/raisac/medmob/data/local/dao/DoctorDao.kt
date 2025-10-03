package com.raisac.medmob.data.local.dao

import androidx.room.*
import com.raisac.medmob.data.local.entity.DoctorEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for Doctor entities
 */
@Dao
interface DoctorDao {
    @Query("SELECT * FROM doctors WHERE uid = :uid")
    fun getDoctorById(uid: String): Flow<DoctorEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDoctor(doctor: DoctorEntity)

    @Update
    suspend fun updateDoctor(doctor: DoctorEntity)

    @Delete
    suspend fun deleteDoctor(doctor: DoctorEntity)

    @Query("DELETE FROM doctors WHERE uid = :uid")
    suspend fun deleteDoctorById(uid: String)
}
