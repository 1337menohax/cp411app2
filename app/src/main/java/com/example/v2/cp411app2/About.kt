package com.example.v2.cp411app2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import mehdi.sakout.aboutpage.AboutPage
import mehdi.sakout.aboutpage.Element

/**About Page
 * DESC: Show the about page using medyo package(google this to know more about this package)
 *
 * CREATED BY: Ivan Vu
 * CREATED DATE:05NOV2018
 * LAST UPDATE: 08NOV2018*/
class About : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        //Initialize back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        /*About page content*/
        val versionElement = Element()
        versionElement.title = "Version 1.0"
        val aboutPage = AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.about_img)
                .addItem(versionElement)
                .addGroup("Found bugs? MSG ME HERE:")
                .addEmail("vivu493@gmail.com")
                .addWebsite("https://github.com/1337menohax")
                .addGitHub("1337menohax/cp411todocalculator")
                //.addInstagram("")
                .setDescription("For  CP411 Android Development App1 Project.")
                .create()
        setContentView(aboutPage)
    }

    /*FUNC:onOptionsItemSelected
    * DESC: Return home when user press the arrow back button
    * */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return true
    }
}


