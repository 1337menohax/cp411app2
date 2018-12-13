package com.example.v2.cp411app2

import android.content.Context
import android.content.res.Resources
import android.databinding.BindingAdapter
import android.databinding.ObservableDouble
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView

object BindingAdapters {
    /**
     * Binding Adapter to replace the Binding Converter that hides a view if the number
     * of likes is zero.
     */
    @BindingAdapter("app:hideIfZero")
    @JvmStatic
    fun hideIfZero(view: View, number: Double) {
        view.visibility = if (number == 0.0) View.GONE else View.VISIBLE
    }
    /**
     * Binding Adapter to set text and its associated color for the status TextView
     */
    @BindingAdapter("app:bmiStatus")
    @JvmStatic
    fun bmiStatus(view: TextView, statusBMI: StatusBMI) {
        view.text = getAssociatedText(statusBMI,view.context)
        view.setTextColor(getAssociatedColor(statusBMI,view.context))
    }

    private fun getAssociatedColor(statusBMI: StatusBMI, context: Context): Int {
        return when (statusBMI) {
            StatusBMI.UNDERWEIGHT -> ContextCompat.getColor(context, R.color.underWeight)
            StatusBMI.NORMAL -> ContextCompat.getColor(context, R.color.normal)
            StatusBMI.OVERWEIGHT -> ContextCompat.getColor(context, R.color.overWeight)
            StatusBMI.OBESE -> ContextCompat.getColor(context, R.color.obese)
        }
    }
    private fun getAssociatedText(statusBMI: StatusBMI, context: Context): String {
        return when (statusBMI) {
            StatusBMI.UNDERWEIGHT -> context.getString(R.string.sl_underweight)
            StatusBMI.NORMAL -> context.getString(R.string.sl_normal)
            StatusBMI.OVERWEIGHT -> context.getString(R.string.sl_overweight)
            StatusBMI.OBESE -> context.getString(R.string.sl_obese)
        }
    }
}