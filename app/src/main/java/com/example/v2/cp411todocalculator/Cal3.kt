package com.example.v2.cp411todocalculator


import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.cal3.*
import android.support.annotation.Nullable
import android.widget.ArrayAdapter
import java.lang.Integer.parseInt


class Cal3 : Fragment(){
    var numerator : Int = 0
    var denominator : Int = 0
    var validNum1= false
    var validNum2=false
    var validDeno1=false
    var validDeno2 = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.cal3, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var list_of_items = arrayOf("+", "-", "*","/")
        spinner_sign.adapter = ArrayAdapter(activity, R.layout.spinner_item, list_of_items)
        spinner_sign.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

           override fun onNothingSelected(p0: AdapterView<*>?) {
               //To change body of created functions use File | Settings | File Templates.
           }
           override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
               //Toast.makeText(activity, list_of_items[p2], LENGTH_LONG).show()
               if (!et_nom1.text.toString().isEmpty()) {
                   validNum1 = true
               }
               if (!et_nom2.text.toString().isEmpty()) {
                   validNum2 = true
               }
               if (!et_denom1.text.toString().isEmpty()) {
                   validDeno1 = true
                   if (getDeno1() == 0){
                       validDeno1 = false
                       tv_frac_result.text = "Cannot divide by 0"
                   }
               }
               if (!et_denom2.text.toString().isEmpty()) {
                   validDeno2 = true
                   if (getDeno2() == 0){
                       validDeno2 = false
                       tv_frac_result.text = "Cannot divide by 0"
                   }
               }
               if (validNum1 && validNum2 && validDeno1 && validDeno2) {
                   if(getSpinner() == "+"){
                       addFrac(getNum1(),getNum2(),getDeno1(),getDeno2())
                   }
                   if(getSpinner() == "-"){
                       subFrac(getNum1(),getNum2(),getDeno1(),getDeno2())
                   }
                   if(getSpinner() == "*"){
                       mulFrac(getNum1(),getNum2(),getDeno1(),getDeno2())
                   }
                   if(getSpinner() == "/"){
                       divFrac(getNum1(),getNum2(),getDeno1(),getDeno2())
                   }
                   setResultText(numerator,denominator)
               }
           }
       }


        bt_compute_frac.setOnClickListener {
            if (!et_nom1.text.toString().isEmpty()) {
                validNum1 = true
            }
            if (!et_nom2.text.toString().isEmpty()) {
                validNum2 = true
            }
            if (!et_denom1.text.toString().isEmpty()) {
                validDeno1 = true
                if (getDeno1() == 0){
                    validDeno1 = false
                    tv_frac_result.text = "Cannot divide by 0"
                }
            }
            if (!et_denom2.text.toString().isEmpty()) {
                validDeno2 = true
                if (getDeno2() == 0){
                    validDeno2 = false
                    tv_frac_result.text = "Cannot divide by 0"
                }
            }
            if (validNum1 && validNum2 && validDeno1 && validDeno2) {
                if(getSpinner() == "+"){
                    addFrac(getNum1(),getNum2(),getDeno1(),getDeno2())
                }
                if(getSpinner() == "-"){
                    subFrac(getNum1(),getNum2(),getDeno1(),getDeno2())
                }
                if(getSpinner() == "*"){
                    mulFrac(getNum1(),getNum2(),getDeno1(),getDeno2())
                }
                if(getSpinner() == "/"){
                    divFrac(getNum1(),getNum2(),getDeno1(),getDeno2())
                }
                if(numerator != 0){
                    setResultText(numerator,denominator)
                }
                else{
                    tv_frac_result.text = ""+ numerator
                }

            }
        }

    }
    private fun getNum1():Int{
        //return et_nom1.text.toString().toInt()
        return parseInt( et_nom1.text.toString())
    }
    private fun getNum2():Int{
        return parseInt( et_nom2.text.toString())
    }
    private fun getDeno1():Int{
        return parseInt( et_denom1.text.toString())
    }
    private fun getDeno2():Int{
        return parseInt( et_denom2.text.toString())
    }
    private fun getSpinner():String{
        return  spinner_sign.selectedItem.toString()
    }
    private fun setResultText( pNum: Int, pDenom: Int){

        //Algorithm to find GCD
        //Always set to positive
         var num = if (pNum > 0) pNum else -pNum
         var denom = if (pDenom > 0) pDenom else -pDenom

        while (num != denom) {
            if (num > denom)
                num -= denom
            else
                denom -= num
            //num is GCD
        }
        var finalNum = pNum / num
        var finalDenom = pDenom / num
        if (finalDenom == 1 || finalNum == 0){
            tv_frac_result.text = ""+ finalNum
        }
        else{
            tv_frac_result.text = ""+ finalNum + "/" + finalDenom
        }
    }
    private fun addFrac(num1:Int, num2: Int, denom1: Int, denom2: Int){
        numerator = (num1*denom2)+(denom1*num2)
        denominator = denom1*denom2
    }
    private fun subFrac(num1:Int, num2: Int, denom1: Int, denom2: Int){
        numerator = (num1*denom2)-(denom1*num2)
        denominator = denom1*denom2
    }
    private fun mulFrac(num1:Int, num2: Int, denom1: Int, denom2: Int){
        numerator = num1*num2
        denominator = denom1*denom2
    }
    private fun divFrac(num1:Int, num2: Int, denom1: Int, denom2: Int){
        numerator = num1*denom2
        denominator = denom1*num2
    }
}
