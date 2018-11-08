package com.example.v2.cp411todocalculator
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.annotation.Nullable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.cal2.*


class Cal2 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.cal2, container, false)
    }
    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        var validFrom= false
        var validTo = false
        bt_perc_calculate.setOnClickListener {
            if (!et_perc_from.text.toString().isEmpty()) {
                validFrom = true
                if(getPercFrom() == 0.0){
                    tv_perc_result.text = getString(R.string.sl_invalid_perc)
                    validFrom = false
                }
            }
            if (!et_perc_to.text.toString().isEmpty()) {
                validTo = true
            }
            if (validFrom && validTo) {
                if(getPercFrom() <= getPercTo()){
                    tv_perc_result.text = String.format("%.1f", calculateIncrease(getPercFrom(),getPercTo())) +"% increase"
                }
                else{
                    tv_perc_result.text = String.format("%.1f", calculateDecrease(getPercFrom(),getPercTo())) +"% decrease"
                }

            }
        }
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
