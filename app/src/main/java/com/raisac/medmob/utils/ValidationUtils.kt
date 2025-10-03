package com.raisac.medmob.utils

import android.util.Patterns
import java.util.regex.Pattern

/**
 * Validation utility class for common input validations
 */
object ValidationUtils {

    /**
     * Validates phone number format
     * Expected format: +256XXXXXXXXX (13 characters)
     */
    fun isValidPhoneNumber(phone: String): Boolean {
        return phone.isNotEmpty() && 
               phone.startsWith("+256") && 
               phone.length == 13 &&
               phone.substring(1).all { it.isDigit() }
    }

    /**
     * Validates email format
     */
    fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    /**
     * Validates verification code
     * Expected: 6 digits
     */
    fun isValidVerificationCode(code: String): Boolean {
        return code.isNotEmpty() && 
               code.length == 6 && 
               code.all { it.isDigit() }
    }

    /**
     * Validates password strength
     * Must be at least 8 characters with uppercase, lowercase, and number
     */
    fun isValidPassword(password: String): Boolean {
        if (password.length < 8) return false
        
        val hasUpperCase = password.any { it.isUpperCase() }
        val hasLowerCase = password.any { it.isLowerCase() }
        val hasDigit = password.any { it.isDigit() }
        
        return hasUpperCase && hasLowerCase && hasDigit
    }

    /**
     * Validates name (no special characters except spaces and hyphens)
     */
    fun isValidName(name: String): Boolean {
        if (name.isEmpty() || name.length < 2) return false
        
        val namePattern = Pattern.compile("^[a-zA-Z\\s-]+$")
        return namePattern.matcher(name).matches()
    }

    /**
     * Validates date of birth format (YYYY-MM-DD)
     */
    fun isValidDateOfBirth(date: String): Boolean {
        val datePattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$")
        return datePattern.matcher(date).matches()
    }

    /**
     * Validates medical license number
     */
    fun isValidLicenseNumber(license: String): Boolean {
        return license.isNotEmpty() && license.length >= 5
    }

    /**
     * Get password strength (0-4)
     * 0 = Very Weak, 1 = Weak, 2 = Medium, 3 = Strong, 4 = Very Strong
     */
    fun getPasswordStrength(password: String): Int {
        var strength = 0
        
        if (password.length >= 8) strength++
        if (password.length >= 12) strength++
        if (password.any { it.isUpperCase() } && password.any { it.isLowerCase() }) strength++
        if (password.any { it.isDigit() }) strength++
        if (password.any { !it.isLetterOrDigit() }) strength++
        
        return strength.coerceIn(0, 4)
    }

    /**
     * Get password strength message
     */
    fun getPasswordStrengthMessage(strength: Int): String {
        return when (strength) {
            0 -> "Very Weak"
            1 -> "Weak"
            2 -> "Medium"
            3 -> "Strong"
            4 -> "Very Strong"
            else -> "Unknown"
        }
    }

    /**
     * Validates blood group
     */
    fun isValidBloodGroup(bloodGroup: String): Boolean {
        val validGroups = listOf("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-")
        return bloodGroup in validGroups
    }
}
