package com.example.v2.cp411app2

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.support.v4.app.FragmentActivity



/**
 * SOURCE: http://www.codebind.com/android-tutorials-and-examples/android-sqlite-tutorial-example/ * Created by ProgrammingKnowledge on 4/3/2015.
 * LAST MODIFIED BY: Ivan Vu
 * LAST MODIFIED DATE: 17DEC18
 */
class DatabaseProfile(context: FragmentActivity?) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1){


    val allData: Cursor
        get() {
            val db = this.writableDatabase
            return db.rawQuery("select * from $TABLE_NAME", null)
            //return db.rawQuery("select * from $TABLE_NAME ", null)
        }
    fun onSelected(name:String): Cursor {
        val db = this.writableDatabase
        return db.rawQuery("select * from $TABLE_NAME where $COL_1 =?", arrayOf(name))
    }
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table $TABLE_NAME (NAME TEXT PRIMARY KEY,FEET DOUBLE,INCHES DOUBLE, POUND DOUBLE)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    fun insertData(name: String, feet: String, inches: String, pound: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues() //new array
        contentValues.put(COL_1, name)
        contentValues.put(COL_2, feet)
        contentValues.put(COL_3, inches)
        contentValues.put(COL_4, pound)
        val result  = db.insert(TABLE_NAME, null, contentValues)
        //if result == -1 then return false, else return true
        return result != (-1).toLong()
    }

    fun updateData(name: String, feet: String, inches: String,pound: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_1, name)
        contentValues.put(COL_2, feet)
        contentValues.put(COL_3, inches)
        contentValues.put(COL_4, pound)
        db.update(TABLE_NAME, contentValues, "NAME = ?", arrayOf(name))
        return true
    }

    fun deleteData(name: String): Int? {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "NAME = ?", arrayOf(name))
    }
    companion object {
        val DATABASE_NAME = "Profile.db"
        val TABLE_NAME = "profile_table"
        val COL_1 = "NAME"
        val COL_2 = "FEET"
        val COL_3 = "INCHES"
        val COL_4 = "POUND"
        val test = "Dom"
    }

}
