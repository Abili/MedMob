package com.raisac.medmob.utils

/**
 * App-wide constants
 */
object Constants {
    // Database
    const val DATABASE_NAME = "medmob_database"
    
    // Shared Preferences
    const val PREFS_NAME = "medmob_prefs"
    
    // Firebase Collections
    const val COLLECTION_USERS = "users"
    const val COLLECTION_DOCTORS = "doctors"
    const val COLLECTION_PATIENTS = "patients"
    const val COLLECTION_APPOINTMENTS = "appointments"
    const val COLLECTION_PRESCRIPTIONS = "prescriptions"
    const val COLLECTION_MEDICAL_RECORDS = "medical_records"
    
    // API Endpoints
    const val API_BASE_URL = "https://api.medmob.com/"
    
    // Request Codes
    const val REQUEST_CODE_PERMISSIONS = 100
    const val REQUEST_CODE_IMAGE_CAPTURE = 101
    const val REQUEST_CODE_IMAGE_PICK = 102
    const val REQUEST_CODE_LOCATION = 103
    const val REQUEST_CODE_BIOMETRIC = 104
    
    // Appointment Status
    const val STATUS_PENDING = "PENDING"
    const val STATUS_CONFIRMED = "CONFIRMED"
    const val STATUS_COMPLETED = "COMPLETED"
    const val STATUS_CANCELLED = "CANCELLED"
    
    // Permissions
    val REQUIRED_PERMISSIONS = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )
}
