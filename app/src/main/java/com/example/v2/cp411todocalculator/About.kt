package com.example.v2.cp411todocalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import mehdi.sakout.aboutpage.AboutPage
import mehdi.sakout.aboutpage.Element


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
                .addGroup("Broke my app!?!?Good!! now msg me")
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


