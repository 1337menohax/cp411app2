package com.example.v2.cp411todocalculator

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.cal2.*
import kotlinx.android.synthetic.main.cal2.view.*
import java.lang.Math.pow



class Cal2 : Fragment() {
    private var userFeet = 0.0
    private var userInch = 0.0
    private var userWeight = 0.0
    var validFeet= false
    var validInch = false
    var validWeight = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.cal2, container, false)
        rootView.bt_computeBmi?.setOnClickListener {
            if (!et_feet.text.toString().isEmpty()) {
                validFeet = true
                if (getFeet() == 0.0){
                    tv_yourBmi.text = getString(R.string.sl_invalid_feet)
                    validFeet = false
                }
            }
            if (!et_inches.text.toString().isEmpty()) {
                validInch = true
            }
            if (!et_weight.text.toString().isEmpty()) {
                validWeight = true
            }

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
        return rootView

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
        return java.lang.Double.parseDouble(et_weight.text.toString())
    }

}
