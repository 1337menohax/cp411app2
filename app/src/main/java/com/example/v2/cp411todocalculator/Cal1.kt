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

class Cal1 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.cal1, container, false)
    }
    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        var validBillAmount = true
        var validTip = true
        var validTax = true

        btCalculate?.setOnClickListener {
            etBillAmount.setText("0.0")
            etTip.setText("15")
            etSaleTax.setText("7.25")
        }
        etBillAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                //To check if string is empty
                //DO: Set valid flag to true for later calculation
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


        etTip.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                //To check if string is empty
                //DO: Set valid flag to true for later calculation
                if(etTip.text.toString().isEmpty() || etTip.text.toString() == ".") {
                    validTip = false
                    tvTotal.text = "$0.00"
                }
                else{

                    validTip = true
                }
            }
            //DO: call calculate()
            override fun afterTextChanged(editable: Editable) {
                if (validBillAmount && validTip && validTax) {
                    tvTotal.text = "$" + String.format("%.2f", calculate(getBillAmount(), getTip(),getTax()))
                }
            }
        })


        etSaleTax.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                //To check if string is not empty
                //DO: Set valid flag to true for later calculation
                if(etSaleTax.text.toString().isEmpty() || etSaleTax.text.toString() == ".") {
                    validTax = false
                    tvTotal.text = "$0.00"
                }
                else{
                    validTax = true
                }
            }
            //DO: call calculate()
            override fun afterTextChanged(editable: Editable) {
                if (validBillAmount && validTip && validTax) {
                    tvTotal.text = "$" + String.format("%.2f", calculate(getBillAmount(), getTip(),getTax()))
                }
            }
        })
    }

    private fun getBillAmount():Double{
        return java.lang.Double.parseDouble(etBillAmount.text.toString())
    }
    private fun getTip():Double{
        return java.lang.Double.parseDouble(etTip.text.toString())
    }
    private fun getTax():Double{
        return java.lang.Double.parseDouble(etSaleTax.text.toString())
    }
    private fun calculate(cost: Double, tip: Double, tax: Double): Double {
        return cost + cost * (tip / 100) + cost * (tax / 100)
    }
}
