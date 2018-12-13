package com.example.v2.cp411app2

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.v2.cp411app2.databinding.MainBmiFragmentBinding
import kotlinx.android.synthetic.main.cal4.*
import java.lang.Math.pow

/**BMI Calculator
 * DESC: Calculate Body Mass Index(BMI) to see if youre normal
 * INPUT:   Feet
 *          Inches
 *          Pound
 *
 * OUTPUT:  BMI and BMI status(normal,healthy,underweight, or obese)
 *
 * CREATED DATE:05NOV2018
 * CREATED BY: Ivan Vu
 * LAST UPDATE: 12DEC2018
 * LAST UPDATED BY: Ivan Vu
 * TODO: fix IF/ELSE into function for easy read*/
class MainBMIFragment : Fragment() {

    //Source: https://codelabs.developers.google.com/codelabs/android-databinding/#1
    //DESC: To obtain ViewModel from ViewModelProviders
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MainBmiViewModel::class.java)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: MainBmiFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.main_bmi_fragment, container, false)
        binding.mainbmiviewmodel = viewModel
        return binding.root
    }
    companion object {
        fun newInstance() = MainBMIFragment()
    }
    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        var validFeet = false
        var validInch = false
        var validWeight = false
        bt_computeBmi?.setOnClickListener {
            et_feet.setText("")
            et_inches.setText("")
            et_pound.setText("")
            tv_statusBMI.text = ""
            tv_yourBmi.text = "0.0"
            validFeet= false
            validInch = false
            validWeight = false

        }

        et_feet.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                //To check if string is not empty
                //DO: Set valid flag to true for later calculation
                //ELSE: Display "invalid input" onto the screen
                if (et_feet.text.toString().isEmpty()|| et_feet.text.toString() == "." ) {
                    validFeet = false
                    tv_yourBmi.text = "0.0"
                } else {
                    validFeet = true
                    if(getFeet() == 0.0){
                        tv_yourBmi.text = getString(R.string.sl_invalid_feet)
                        validFeet = false
                    }
                }
            }
            //If validFrom and validTo flags are BOTH true, start calculating
            //DO: call calculate()
            override fun afterTextChanged(editable: Editable) {
                if (validFeet && validInch && validWeight){
                    val resultBmi = calculate(getFeet(), getInch(), getWeight())
                    tv_yourBmi.text = String.format("%.1f", resultBmi)
                    if(resultBmi in 0.0..18.4){
                        tv_statusBMI.setTextColor(ContextCompat.getColor(activity!!.applicationContext, R.color.underWeight))
                        tv_statusBMI.text = "Underweight"
                    }
                    else if (resultBmi in 18.5..24.9){
                        tv_statusBMI.setTextColor(ContextCompat.getColor(activity!!.applicationContext, R.color.normal))
                        tv_statusBMI.text = "Normal"
                    }
                    else if (resultBmi in 25.0..29.9){
                        tv_statusBMI.setTextColor(ContextCompat.getColor(activity!!.applicationContext, R.color.overWeight))
                        tv_statusBMI.text = "Overweight"
                    }
                    else if (resultBmi >= 30.0){
                        tv_statusBMI.setTextColor(ContextCompat.getColor(activity!!.applicationContext, R.color.obese))
                        tv_statusBMI.text = "Obese"
                    }
                }
            }
        })

        et_inches.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                //To check if string is not empty
                //DO: Set valid flag to true for later calculation
                //ELSE: Display "invalid input" onto the screen
                if (et_inches.text.toString().isEmpty()|| et_inches.text.toString() == "." ) {
                    validInch = false
                    tv_yourBmi.text = "0.0"
                } else {
                    validInch = true
                }
            }
            //If validFrom and validTo flags are BOTH true, start calculating
            //DO: call calculate()
            override fun afterTextChanged(editable: Editable) {
                if (validFeet && validInch && validWeight){
                    val resultBmi = calculate(getFeet(), getInch(), getWeight())
                    tv_yourBmi.text = String.format("%.1f", resultBmi)
                    if(resultBmi in 0.0..18.4){
                        tv_statusBMI.setTextColor(ContextCompat.getColor(activity!!.applicationContext, R.color.underWeight))
                        tv_statusBMI.text = "Underweight"
                    }
                    else if (resultBmi in 18.5..24.9){
                        tv_statusBMI.setTextColor(ContextCompat.getColor(activity!!.applicationContext, R.color.normal))
                        tv_statusBMI.text = "Normal"
                    }
                    else if (resultBmi in 25.0..29.9){
                        tv_statusBMI.setTextColor(ContextCompat.getColor(activity!!.applicationContext, R.color.overWeight))
                        tv_statusBMI.text = "Overweight"
                    }
                    else if (resultBmi >= 30.0){
                        tv_statusBMI.setTextColor(ContextCompat.getColor(activity!!.applicationContext, R.color.obese))
                        tv_statusBMI.text = "Obese"
                    }
                }
            }
        })

        et_pound.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                //To check if string is not empty
                //DO: Set valid flag to true for later calculation
                //ELSE: Display "invalid input" onto the screen
                if (et_pound.text.toString().isEmpty()|| et_pound.text.toString() == "." ) {
                    validWeight = false
                    tv_yourBmi.text = "0.0"
                } else {
                    validWeight = true
                }
            }
            //If validFrom and validTo flags are BOTH true, start calculating
            //DO: call calculate()
            override fun afterTextChanged(editable: Editable) {
                if (validFeet && validInch && validWeight){
                    val resultBmi = calculate(getFeet(), getInch(), getWeight())
                    tv_yourBmi.text = String.format("%.1f", resultBmi)
                    if(resultBmi in 0.0..18.4){
                        tv_statusBMI.setTextColor(ContextCompat.getColor(activity!!.applicationContext, R.color.underWeight))
                        tv_statusBMI.text = "Underweight"
                    }
                    else if (resultBmi in 18.5..24.9){
                        tv_statusBMI.setTextColor(ContextCompat.getColor(activity!!.applicationContext, R.color.normal))
                        tv_statusBMI.text = "Normal"
                    }
                    else if (resultBmi in 25.0..29.9){
                        tv_statusBMI.setTextColor(ContextCompat.getColor(activity!!.applicationContext, R.color.overWeight))
                        tv_statusBMI.text = "Overweight"
                    }
                    else if (resultBmi >= 30.0){
                        tv_statusBMI.setTextColor(ContextCompat.getColor(activity!!.applicationContext, R.color.obese))
                        tv_statusBMI.text = "Obese"
                    }
                }
            }
        })
    }

    private fun calculate(feet: Double, inch: Double, weight: Double): Double {
        return(weight/ pow((feet * 12) + inch, 2.0))*703
    }
    private fun getFeet():Double{
        return java.lang.Double.parseDouble(et_feet.text.toString())
    }
    private fun getInch():Double{
        return java.lang.Double.parseDouble(et_inches.text.toString())
    }
    private fun getWeight():Double{
        return java.lang.Double.parseDouble(et_pound.text.toString())
    }

}
