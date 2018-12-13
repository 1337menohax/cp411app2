package com.example.v2.cp411app2

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText


/**BMI
 * DESC: This is a multi calculator designed for CP411 Android development APP 1 project.
 * CREATED DATE:05NOV2018
 * CREATED BY: Ivan Vu
 * LAST UPDATE: 12DEC2018
 * LAST UPDATED BY: Ivan Vu*/

class MainActivity : AppCompatActivity() {

    var isFragmentDisplayed = false
    private val STATE_FRAGMENT = "state_of_fragment"

    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        // Save the state of the fragment (true=open, false=closed).
        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed)
        super.onSaveInstanceState(savedInstanceState)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) { //if bundle were saved successful
            isFragmentDisplayed =
                    savedInstanceState.getBoolean(STATE_FRAGMENT)
        }
        displayFragment()
    }

    /**
     * FUNC: onCreateOptionsMenu, onOptionsItemSelected
     * DESC: inflating option menu on App bar and handle on selected item
     * CURRENT OPTION: "About"
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.action_about) {
            val myIntent = Intent(this@MainActivity, About::class.java)
            this@MainActivity.startActivity(myIntent)
            true
        } else super.onOptionsItemSelected(item)

    }/******** END OF FUNCS FOR OPTION MENU********/



    /**
     * FUNC: dispatchTouchEvent
     * DESC: Defocus EditText when user touch outside of EditText
     */
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    private fun displayFragment() {
        var mainFragment = MainBMIFragment.newInstance()

        //Get the FragmentManager and start a transaction.
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager
                .beginTransaction()

        // Add the SimpleFragment.
        fragmentTransaction.add(R.id.fragment_container,
                mainFragment).addToBackStack(null).commit()
        // Update the Button text.
        ////open_button.setText(R.string.close_button)
        // Set boolean flag to indicate fragment is open.
        isFragmentDisplayed = true
    }


}
