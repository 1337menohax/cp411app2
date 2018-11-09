package com.example.v2.cp411todocalculator

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.annotation.Nullable
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.cal1.*
/**Tip/Tax Calculator
 * DESC: Calculate total bill amount
 * INPUT:Bill Amount, Tip, Tax
 * OUTPUT: Total price
 * CREATED BY: Ivan Vu
 * LAST UPDATE: 08NOV2018*/
class Cal1 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.cal1, container, false)
    }
    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        var validBillAmount = true
        var validTip = true
        var validTax = true

        //btCalculate AKA reset button
        //TODO: change btCalculate ID to btReset for easy read
        btCalculate?.setOnClickListener {
            etBillAmount.setText("0.0")
            etTip.setText("15")
            etSaleTax.setText("7.25")
        }
        /**Listen to editText of BILL AMOUNT and update flag*/
        etBillAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                //To check if string is empty or has decimal period at the beginning
                //DO: Set valid flag to false, otherwise proceed
                if(etBillAmount.text.toString().isEmpty() || etBillAmount.text.toString() == ".") {
                    validBillAmount = false
                    tvTotal.text = ("$0.00")
                }
                else{
                    validBillAmount = true
                }

            }
            //If validBillAmount and validTip and validTax flags are ALL true, start calculating
            //DO: call calculate()
            override fun afterTextChanged(editable: Editable) {
                if (validBillAmount && validTip && validTax) {
                    tvTotal.text = "$" + String.format("%.2f", calculate(getBillAmount(), getTip(),getTax()))
                }
            }
        })
        /**Listen to editText of TIP and update flag*/
        etTip.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                //To check if string is empty or has decimal period at the beginning
                //DO: Set valid flag to false, otherwise proceed
                if(etTip.text.toString().isEmpty() || etTip.text.toString() == ".") {
                    validTip = false
                    tvTotal.text = "$0.00"
                }
                else{
                    validTip = true
                }
            }
            override fun afterTextChanged(editable: Editable) {
                if (validBillAmount && validTip && validTax) {
                    tvTotal.text = "$" + String.format("%.2f", calculate(getBillAmount(), getTip(),getTax()))
                }
            }
        })

        /**Listen to editText of TAX and update flag*/
        etSaleTax.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if(etSaleTax.text.toString().isEmpty() || etSaleTax.text.toString() == ".") {
                    validTax = false
                    tvTotal.text = "$0.00"
                }
                else{
                    validTax = true
                }
            }
            override fun afterTextChanged(editable: Editable) {
                if (validBillAmount && validTip && validTax) {
                    tvTotal.text = "$" + String.format("%.2f", calculate(getBillAmount(), getTip(),getTax()))
                }
            }
        })
    }
    /**FUNC: get*
     * RETURN: Double decimal from editText*/
    private fun getBillAmount():Double{
        return java.lang.Double.parseDouble(etBillAmount.text.toString())
    }
    private fun getTip():Double{
        return java.lang.Double.parseDouble(etTip.text.toString())
    }
    private fun getTax():Double{
        return java.lang.Double.parseDouble(etSaleTax.text.toString())
    }
    /**FUNC: calculate
     * INPUT:   Double cost
     *          Double tip
     *          Double tax
     * RETURN:  Double total cost*/
    private fun calculate(cost: Double, tip: Double, tax: Double): Double {
        return cost + cost * (tip / 100) + cost * (tax / 100)
    }
}
