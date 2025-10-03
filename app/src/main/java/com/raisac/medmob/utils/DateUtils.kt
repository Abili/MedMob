package com.raisac.medmob.utils

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Date utility functions
 */
object DateUtils {

    const val FORMAT_DATE = "yyyy-MM-dd"
    const val FORMAT_TIME = "HH:mm"
    const val FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss"
    const val FORMAT_DISPLAY_DATE = "MMM dd, yyyy"
    const val FORMAT_DISPLAY_TIME = "hh:mm a"
    const val FORMAT_DISPLAY_DATE_TIME = "MMM dd, yyyy hh:mm a"

    /**
     * Get current timestamp in milliseconds
     */
    fun getCurrentTimestamp(): Long = System.currentTimeMillis()

    /**
     * Format timestamp to date string
     */
    fun formatTimestamp(
        timestamp: Long,
        format: String = FORMAT_DISPLAY_DATE_TIME
    ): String {
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        return sdf.format(Date(timestamp))
    }

    /**
     * Parse date string to timestamp
     */
    fun parseDate(dateString: String, format: String = FORMAT_DATE): Long? {
        return try {
            val sdf = SimpleDateFormat(format, Locale.getDefault())
            sdf.parse(dateString)?.time
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Get relative time string (e.g., "2 hours ago")
     */
    fun getRelativeTime(timestamp: Long): String {
        val now = getCurrentTimestamp()
        val diff = now - timestamp

        return when {
            diff < TimeUnit.MINUTES.toMillis(1) -> "Just now"
            diff < TimeUnit.HOURS.toMillis(1) -> {
                val minutes = TimeUnit.MILLISECONDS.toMinutes(diff)
                "$minutes ${if (minutes == 1L) "minute" else "minutes"} ago"
            }
            diff < TimeUnit.DAYS.toMillis(1) -> {
                val hours = TimeUnit.MILLISECONDS.toHours(diff)
                "$hours ${if (hours == 1L) "hour" else "hours"} ago"
            }
            diff < TimeUnit.DAYS.toMillis(7) -> {
                val days = TimeUnit.MILLISECONDS.toDays(diff)
                "$days ${if (days == 1L) "day" else "days"} ago"
            }
            diff < TimeUnit.DAYS.toMillis(30) -> {
                val weeks = TimeUnit.MILLISECONDS.toDays(diff) / 7
                "$weeks ${if (weeks == 1L) "week" else "weeks"} ago"
            }
            else -> formatTimestamp(timestamp, FORMAT_DISPLAY_DATE)
        }
    }

    /**
     * Calculate age from date of birth
     */
    fun calculateAge(dateOfBirth: String, format: String = FORMAT_DATE): Int? {
        val birthDate = parseDate(dateOfBirth, format) ?: return null
        val today = Calendar.getInstance()
        val birth = Calendar.getInstance().apply {
            time = Date(birthDate)
        }

        var age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR)
        
        if (today.get(Calendar.DAY_OF_YEAR) < birth.get(Calendar.DAY_OF_YEAR)) {
            age--
        }

        return age
    }

    /**
     * Check if date is today
     */
    fun isToday(timestamp: Long): Boolean {
        val today = Calendar.getInstance()
        val date = Calendar.getInstance().apply {
            time = Date(timestamp)
        }

        return today.get(Calendar.YEAR) == date.get(Calendar.YEAR) &&
               today.get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR)
    }

    /**
     * Check if date is in the future
     */
    fun isFuture(timestamp: Long): Boolean {
        return timestamp > getCurrentTimestamp()
    }

    /**
     * Check if date is in the past
     */
    fun isPast(timestamp: Long): Boolean {
        return timestamp < getCurrentTimestamp()
    }

    /**
     * Add days to current date
     */
    fun addDays(days: Int, from: Long = getCurrentTimestamp()): Long {
        return from + TimeUnit.DAYS.toMillis(days.toLong())
    }

    /**
     * Add hours to current date
     */
    fun addHours(hours: Int, from: Long = getCurrentTimestamp()): Long {
        return from + TimeUnit.HOURS.toMillis(hours.toLong())
    }

    /**
     * Get start of day timestamp
     */
    fun getStartOfDay(timestamp: Long = getCurrentTimestamp()): Long {
        return Calendar.getInstance().apply {
            time = Date(timestamp)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis
    }

    /**
     * Get end of day timestamp
     */
    fun getEndOfDay(timestamp: Long = getCurrentTimestamp()): Long {
        return Calendar.getInstance().apply {
            time = Date(timestamp)
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 59)
            set(Calendar.MILLISECOND, 999)
        }.timeInMillis
    }

    /**
     * Format duration in minutes to readable string
     */
    fun formatDuration(minutes: Int): String {
        return when {
            minutes < 60 -> "$minutes min"
            minutes % 60 == 0 -> "${minutes / 60} hr"
            else -> "${minutes / 60} hr ${minutes % 60} min"
        }
    }
}
