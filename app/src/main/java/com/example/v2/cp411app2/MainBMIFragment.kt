package com.example.v2.cp411app2

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.v2.cp411app2.databinding.MainBmiFragmentBinding
import kotlinx.android.synthetic.main.main_bmi_fragment.*

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Opening SQLite
        val dbEntry: DatabaseProfile = DatabaseProfile(activity)
        super.onViewCreated(view, savedInstanceState)
        val listSign = arrayOf("Ivan", "Sunny", "Dom", "New Profile")
        spinner_profile.adapter = ArrayAdapter(activity, R.layout.spinner_item, listSign)
        spinner_profile.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                //TODO do stuff when a profile is selected
                val isInserted = dbEntry.insertData(spinner_profile.selectedItem.toString(),
                        et_feet.text.toString(),
                        et_inches.text.toString(),
                        et_pound.text.toString())

                if (isInserted)
                    Toast.makeText(activity, "Data Inserted", Toast.LENGTH_SHORT).show()
                else {
                    Toast.makeText(activity, "Data not Inserted", Toast.LENGTH_SHORT).show()
                    val res = dbEntry.onSelected(spinner_profile.selectedItem.toString()) //Set cursor to the selected profile according to the spinner

                    if (res.count == 0) { //if number of row is 0 then show no data found.
                        Toast.makeText(activity, "No Data Found", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        res.moveToFirst()
                        et_feet.setText( res.getString(res.getColumnIndex("FEET")))
                        et_inches.setText( res.getString(res.getColumnIndex("INCHES")))
                        et_pound.setText( res.getString(res.getColumnIndex("POUND")))
                    }


                }
            }
        }

        add_button.setOnClickListener {
            val isInserted = dbEntry.insertData(spinner_profile.selectedItem.toString(),
                    et_feet.text.toString(),
                    et_inches.text.toString(),
                    et_pound.text.toString())
            if (isInserted)
                Toast.makeText(activity, "Data Inserted", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(activity, "Data not Inserted", Toast.LENGTH_SHORT).show()
        }
        view_all_button.setOnClickListener {
            val res = dbEntry.allData
            if (res.count == 0) {
                // show message
                Toast.makeText(activity, "No Data Found", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else{

                val buffer = StringBuffer()
                while (res.moveToNext()) {
                    buffer.append("Name :" + res.getString(0) + "\n")
                    buffer.append("Feet :" + res.getString(1) + "\n")
                    buffer.append("Inches :" + res.getString(2) + "\n")
                    buffer.append("Pound :" + res.getString(3) + "\n\n")
                }

                // Show all data
                Toast.makeText(activity, buffer.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        bt_save_profile.setOnClickListener {
            val isUpdate = dbEntry.updateData(spinner_profile.selectedItem.toString(),
                    et_feet.text.toString(),
                    et_inches.text.toString(),
                    et_pound.text.toString())
            if (isUpdate == true)
                Toast.makeText(activity, "Data Update", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(activity, "Data not Updated", Toast.LENGTH_SHORT).show()
        }


        deletebutton.setOnClickListener {
            val deletedRows = dbEntry.deleteData(spinner_profile.selectedItem.toString())
            if(deletedRows!! > 0)
                Toast.makeText(activity,"Data Deleted",Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(activity,"Data not Deleted",Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newInstance() = MainBMIFragment()
    }

}