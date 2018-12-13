/*
package com.example.v2.cp411app2
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.cal2.*

*/
/**Percentage Different Calculator
 * DESC: Calculate the percentage between original number and new number
 * INPUT:   Original number
 *          New number
 *
 * OUTPUT:  Percentage Different
 * CREATED BY: Ivan Vu
 * CREATED DATE:05NOV2018
 * LAST UPDATE: 08NOV2018
 * TODO: Put checking IF/ELSE into functions for easy read*//*


class Cal2 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.cal2, container, false)
    }
    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        var validFrom= false
        var validTo = false

        //bt_perc_calculate AKA reset button
        //TODO: change bt_perc_calculate ID to something reset for easy read
        bt_perc_calculate.setOnClickListener {
            et_perc_from.setText("")
            et_perc_to.setText("")
            tv_perc_result.text = "0.0% increase/decrease"
            tv_perc_result.setTextColor(ContextCompat.getColor(activity!!.applicationContext, R.color.underWeight))
            validFrom= false
            validTo = false
        }

        et_perc_from.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (et_perc_from.text.toString().isEmpty()|| et_perc_from.text.toString() == "." || et_perc_from.text.toString() == "-") {
                    validFrom = false
                    tv_perc_result.text = "0.0% increase/decrease"
                    tv_perc_result.setTextColor(ContextCompat.getColor(activity!!.applicationContext, R.color.underWeight))
                } else {
                    validFrom = true
                    if(getPercFrom() == 0.0){
                        tv_perc_result.text = getString(R.string.sl_invalid_perc)
                        validFrom = false
                    }
                }
            }
            //If validFrom and validTo flags are BOTH true, start calculating
            //DO: call calculate()
            override fun afterTextChanged(editable: Editable) {
                if (validFrom && validTo) {
                    if(getPercFrom() <= getPercTo()){
                        tv_perc_result.text = String.format("%.1f", calculateIncrease(getPercFrom(),getPercTo())) +"% increase"
                        tv_perc_result.setTextColor(ContextCompat.getColor(activity!!.applicationContext, R.color.normal))
                    }
                    else{
                        tv_perc_result.text = String.format("%.1f", calculateDecrease(getPercFrom(),getPercTo())) +"% decrease"
                        tv_perc_result.setTextColor(ContextCompat.getColor(activity!!.applicationContext, R.color.obese))
                    }
                }
            }
        })


        et_perc_to.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (et_perc_to.text.toString().isEmpty()|| et_perc_to.text.toString() == "."|| et_perc_to.text.toString() == "-") {
                    validTo = false
                    tv_perc_result.text = "0.0% increase/decrease"
                    tv_perc_result.setTextColor(ContextCompat.getColor(activity!!.applicationContext, R.color.underWeight))
                } else {
                    validTo = true
                    //DESC: to show invalid string on result when et_perc_from is 0
                    if(!et_perc_from.text.toString().isEmpty()){
                        if(getPercFrom() == 0.0){
                            tv_perc_result.text = getString(R.string.sl_invalid_perc)
                            validFrom = false
                        }
                    }
                }
            }
            //If validFrom and validTo flags are BOTH true, start calculating
            //DO: call calculate()
            override fun afterTextChanged(editable: Editable) {
                if (validFrom && validTo) {
                    if(getPercFrom() <= getPercTo()){
                        tv_perc_result.text = String.format("%.1f", calculateIncrease(getPercFrom(),getPercTo())) +"% increase"
                        tv_perc_result.setTextColor(ContextCompat.getColor(activity!!.applicationContext, R.color.normal))
                    }
                    else{
                        tv_perc_result.text = String.format("%.1f", calculateDecrease(getPercFrom(),getPercTo())) +"% decrease"
                        tv_perc_result.setTextColor(ContextCompat.getColor(activity!!.applicationContext, R.color.obese))
                    }
                }
            }
        })
    }

    private fun calculateIncrease(oriNum: Double, newNum: Double): Double {
        return ((newNum-oriNum)/oriNum) * 100
    }
    private fun calculateDecrease(oriNum: Double, newNum: Double): Double {
        return ((oriNum-newNum)/oriNum) * 100
    }
    private fun getPercFrom():Double{
        return java.lang.Double.parseDouble(et_perc_from.text.toString())
    }
    private fun getPercTo():Double{
        return java.lang.Double.parseDouble(et_perc_to.text.toString())
    }

}
*/
