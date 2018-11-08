package com.example.v2.cp411todocalculator

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.annotation.Nullable
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
        var userBill = 0.0
        var userTip = 0.0
        var userTax = 0.0
        btCalculate?.setOnClickListener {
            if (!etBillAmount.text.toString().isEmpty()) {
                userBill = java.lang.Double.parseDouble(etBillAmount.text.toString())
            }
            if (!etTip.text.toString().isEmpty()) {
                userTip = java.lang.Double.parseDouble(etTip.text.toString())
            }
            if (!etSaleTax.text.toString().isEmpty()) {
                userTax = java.lang.Double.parseDouble(etSaleTax.text.toString())
            }
            tvTotal.text = "$" + String.format("%.2f", calculate(userBill, userTip, userTax))
        }
    }


    private fun calculate(cost: Double, tip: Double, tax: Double): Double {
        return cost + cost * (tip / 100) + cost * (tax / 100)
    }
}
