/*
package com.example.v2.cp411app2
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.cal3.*
import android.support.annotation.Nullable
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import java.lang.Integer.parseInt

*/
/**Fraction Calculator
 * DESC: Calculate fractions
 * INPUT:   2 Numerators
 *          2 Denominator
 *
 * OUTPUT:  Fraction in reduced form
 * CREATED BY: Ivan Vu
 * CREATED DATE:05NOV2018
 * LAST UPDATE: 08NOV2018*//*


class Cal3 : Fragment(){
    var numerator : Int = 0
    var denominator : Int = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.cal3, container, false)
    }
    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Initializing spinner for different signs: + - * /
        val listSign = arrayOf("+", "-", "*","/")
        spinner_sign.adapter = ArrayAdapter(activity, R.layout.spinner_item, listSign)
        spinner_sign.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
               //To change body of created functions use File | Settings | File Templates.
           }
           override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
               if (checkAllNum()){
                   checkSpinner()
               }
           }
       }
        //bt_compute_frac AKA reset button
        //TODO: change bt_compute_frac ID to something reset for easy read
        bt_compute_frac.setOnClickListener {
            et_nom1.setText("")
            et_nom2.setText("")
            et_denom1.setText("")
            et_denom2.setText("")
        }
        */
/******START OF NUMERATOR 1***//*

        et_nom1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                checkAllNum()
            }
            override fun afterTextChanged(editable: Editable) {
                if (checkAllNum()){
                    checkSpinner()
                }
            }
        })
        */
/******END OF NUMERATOR 1***//*

        */
/******START OF NUMERATOR 2***//*

        et_nom2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                checkAllNum()
            }
            override fun afterTextChanged(editable: Editable) {
                if (checkAllNum()){
                    checkSpinner()
                }
            }
        })
        */
/******END OF NUMERATOR 2***//*

        */
/******START OF DENOMINATOR 1***//*

        et_denom1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                checkAllNum()
            }
            override fun afterTextChanged(editable: Editable) {
                if (checkAllNum()){
                    checkSpinner()
                }
            }
        })
        */
/******END OF DENOMINATOR 1***//*

        */
/******START OF DENOMINATOR 2***//*

        et_denom2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                checkAllNum()
            }
            //If validFrom and validTo flags are BOTH true, start calculating
            //DO: call calculate()
            override fun afterTextChanged(editable: Editable) {
                if (checkAllNum()){
                    checkSpinner()
                }
            }
        })
        */
/******END OF DENOMINATOR 2***//*

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
    */
/**FUNC: setResultText
     * DESC: Calculate the greatest common divider and show the result
     * INPUT:   Numerator
     *          Denominator
     *
     * OUT:     Result in fraction form
     * TODO: Clean up variable names for easy read*//*

    private fun setResultText( pNum: Int, pDenom: Int){

        //Algorithm to find GCD start

        //Always set numerator and denominator to positive
         var num = if (pNum > 0) pNum else -pNum
         var denom = if (pDenom > 0) pDenom else -pDenom

        while (num != denom) {
            if (num > denom)
                num -= denom
            else
                denom -= num
            //num is GCD
        }
        val finalNum = pNum / num
        val finalDenom = pDenom / num

        if (finalDenom == 1 || finalNum == 0){
            tv_frac_result.text = "$finalNum"
        }
        else{
            tv_frac_result.text = "$finalNum/$finalDenom"
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
    */
/**FUNC: checkAllNum
     * DESC: Making sure all editText are good to for calculation
     * INPUT:
     * OUT:     True if all fills are good, otherwise False*//*

    fun checkAllNum():Boolean{
        var validNum1= false
        var validNum2= false
        var validDeno1= false
        var validDeno2 = false
        if (!(et_nom1.text.toString().isEmpty()||et_nom1.text.toString() == "-")) {
            validNum1 = true
        }
        else{
            tv_frac_result.text = "0"
            validNum1 = false
        }

        if (!(et_nom2.text.toString().isEmpty()||et_nom2.text.toString() == "-")) {
            validNum2 = true
        }
        else{
            tv_frac_result.text = "0"
            validNum2 = false
        }
        if (!(et_denom1.text.toString().isEmpty()||et_denom1.text.toString() == "-")) {
            validDeno1 = true
            if (getDeno1() == 0){
                validDeno1 = false
                tv_frac_result.text = "Cannot divide by 0"
            }
        }
        if (!(et_denom2.text.toString().isEmpty()||et_denom2.text.toString() == "-")) {
            validDeno2 = true
            if (getDeno2() == 0){
                validDeno2 = false
                tv_frac_result.text = "Cannot divide by 0"
            }
        }
        return validNum1 && validNum2 && validDeno1 && validDeno2
    }
    */
/**FUNC: checkSpinner
     * DESC: check which signs on the spinner
     * DO: call calculate function based on signs that is pick*//*

    private fun checkSpinner(){
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
            tv_frac_result.text = "0"
        }
    }

}
*/
