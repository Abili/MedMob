package com.raisac.medmob.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * Extension functions for Android Context
 */

/**
 * Show a short toast message
 */
fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

/**
 * Show a long toast message
 */
fun Context.showLongToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

/**
 * Hide keyboard from view
 */
fun Context.hideKeyboard(view: View) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

/**
 * Show keyboard for view
 */
fun Context.showKeyboard(view: View) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}

/**
 * Extension functions for Fragment
 */

/**
 * Show a short toast message
 */
fun Fragment.showToast(message: String) {
    requireContext().showToast(message)
}

/**
 * Show a long toast message
 */
fun Fragment.showLongToast(message: String) {
    requireContext().showLongToast(message)
}

/**
 * Hide keyboard
 */
fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

/**
 * Extension functions for View
 */

/**
 * Make view visible
 */
fun View.visible() {
    visibility = View.VISIBLE
}

/**
 * Make view invisible
 */
fun View.invisible() {
    visibility = View.INVISIBLE
}

/**
 * Make view gone
 */
fun View.gone() {
    visibility = View.GONE
}

/**
 * Toggle view visibility between VISIBLE and GONE
 */
fun View.toggleVisibility() {
    visibility = if (visibility == View.VISIBLE) View.GONE else View.VISIBLE
}

/**
 * Check if view is visible
 */
fun View.isVisible(): Boolean = visibility == View.VISIBLE

/**
 * Set view visibility based on boolean
 */
fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}
