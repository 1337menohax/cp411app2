package com.example.v2.cp411app2

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableDouble
import android.databinding.ObservableField
import java.text.DecimalFormat
import android.R.attr.name
import android.widget.Toast





class MainBmiViewModel : ViewModel() {
    var validFeet = false
    var validInch = false
    var validWeight = false
    var ldFeet = 0.0
    var ldInches = 0.0
    var ldWeight = 0.0
    val resultBMI = ObservableDouble()
    val statusBMI = ObservableField<StatusBMI>(StatusBMI.NORMAL)
    /** FUNC: onFeet()
     * DESC: Call when theres a text change in Height editview
     * */

    fun onFeet(s:CharSequence){
        if (s.toString().isEmpty() || s.toString() == ".") {
            validFeet = false
            resultBMI.set(0.0)

        }
        else {
            validFeet = true
            ldFeet = s.toString().toDouble()
            if (ldFeet == 0.0){
                validFeet = false
                resultBMI.set(0.0)
            }
        }
        if (validFeet && validInch && validWeight) {
            resultBMI.set(calculate(ldFeet,ldInches,ldWeight))

            statusBMI.set(resultBMI.get().let {
                if (it in 0.0..18.4) StatusBMI.UNDERWEIGHT
                else if (it in 18.5..24.9) StatusBMI.NORMAL
                else if (it in 25.0..29.9) StatusBMI.OVERWEIGHT
                else if (it >= 30.0) StatusBMI.OBESE
                else StatusBMI.OBESE
            })
            }
        }
    /** FUNC: onInches()
     * DESC: Call when theres a text change in Inches editview
     * */
    fun onInches(s:CharSequence){
        if (s.toString().isEmpty() || s.toString() == ".") {
            validInch = false
            resultBMI.set(0.0)
        }
        else {
            validInch = true
            ldInches = s.toString().toDouble()
        }
        if (validFeet && validInch && validWeight) {
            resultBMI.set(calculate(ldFeet,ldInches,ldWeight))
            statusBMI.set(resultBMI.get().let {
                if (it in 0.0..18.4) StatusBMI.UNDERWEIGHT
                else if (it in 18.5..24.9) StatusBMI.NORMAL
                else if (it in 25.0..29.9) StatusBMI.OVERWEIGHT
                else if (it >= 30.0) StatusBMI.OBESE
                else StatusBMI.OBESE
            })
        }
    }
    /** FUNC: onPound()
     * DESC: Call when theres a text change in Pound editview
     * */
    fun onPound(s:CharSequence){
        if (s.toString().isEmpty() || s.toString() == ".") {
            validWeight = false
            resultBMI.set(0.0)
        }
        else {
            validWeight = true
            ldWeight = s.toString().toDouble()
        }
        if (validFeet && validInch && validWeight) {
            resultBMI.set(calculate(ldFeet,ldInches,ldWeight))

            statusBMI.set(resultBMI.get().let {
                if (it in 0.0..18.4) StatusBMI.UNDERWEIGHT
                else if (it in 18.5..24.9) StatusBMI.NORMAL
                else if (it in 25.0..29.9) StatusBMI.OVERWEIGHT
                else if (it >= 30.0) StatusBMI.OBESE
                else StatusBMI.OBESE
            })
        }
    }
}
    private fun calculate(feet: Double, inch: Double, weight: Double): Double {
        val result = (weight/ Math.pow((feet * 12) + inch, 2.0))*703
        val df = DecimalFormat("#.#")
        return java.lang.Double.valueOf(df.format(result))
    }
enum class StatusBMI {
    UNDERWEIGHT,
    NORMAL,
    OVERWEIGHT,
    OBESE
}