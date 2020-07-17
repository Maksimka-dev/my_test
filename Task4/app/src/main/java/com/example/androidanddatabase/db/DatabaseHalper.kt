package com.example.androidanddatabase.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.androidanddatabase.lib.Cat


internal const val COLUMN_NAME = "CAT_NAME"
internal const val COLUMN_AGE = "CAT_AGE"
internal const val COLUMN_BREED = "CAT_BREED"

private const val LOG_TAG = "SQLiteOpenHelper"
private const val DATABASE_NAME = "TASK_ONE"
private const val TABLE_NAME = "my_cats"
private const val DATABASE_VERSION = 2
private const val CREATE_TABLE_SQL =
    "CREATE TABLE IF NOT EXISTS $TABLE_NAME (_id INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_NAME VARCHAR(50), $COLUMN_AGE INTEGER, $COLUMN_BREED VARCHAR(50));"

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    @SuppressLint("LongLogTag")
    override fun onCreate(db: SQLiteDatabase) {
        try {
            db.execSQL(CREATE_TABLE_SQL)
        } catch (exception: SQLException) {
            Log.e(LOG_TAG, "Exception while trying to create database", exception)
        }
    }

    @SuppressLint("LongLogTag")
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.d(LOG_TAG, "onUpgrade called")
    }

    private fun getCursorWithTopics(sort: String? = null): Cursor {
        return if (sort == null)
            readableDatabase.rawQuery("SELECT * FROM $TABLE_NAME", null)
        else
            readableDatabase.rawQuery("SELECT * FROM $TABLE_NAME ORDER BY $sort ASC", null)
    }

    fun insertQuery(name: String, age: Int, breed: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, name)
        values.put(COLUMN_AGE, age)
        values.put(COLUMN_BREED, breed)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getListOfTopics(sort: String? = null): List<Cat> {
        val listOfTopics = mutableListOf<Cat>()
        getCursorWithTopics(sort).use { cursor ->
            if (cursor.moveToFirst()) {
                do {
                    val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                    val age = cursor.getInt(cursor.getColumnIndex(COLUMN_AGE))
                    val breed = cursor.getString(cursor.getColumnIndex(COLUMN_BREED))
                    listOfTopics.add(
                        Cat(name, age, breed)
                    )
                } while (cursor.moveToNext())
            }
        }
        return listOfTopics
    }
}