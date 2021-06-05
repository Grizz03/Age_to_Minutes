package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

// ID's->
// selectedDate
// ageInMinutes

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myButton = findViewById<Button>(R.id.button)

        myButton.setOnClickListener { view ->
            clickDatePicker(view)
            Toast.makeText(this, "button works", Toast.LENGTH_SHORT).show()
        }
    }

    // Date Picker function
    fun clickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this,
            { view,
              selectedYear,
              selectedMonth,
              selectedDayOfMonth ->
                // Format for date
                val userSelectedDate = "${selectedMonth+1}/$selectedDayOfMonth/$selectedYear"
                // Create variable for text view
                val mySelectedDate = findViewById<TextView>(R.id.selectedDate)
                // Setting TextView to userSelectedDate string
                mySelectedDate.text = userSelectedDate

                // Parsing date
                val sdf = SimpleDateFormat("dd/MM/yyy", Locale.ENGLISH)
                val theDate = sdf.parse(userSelectedDate)

                // Converts date to milliseconds, divide by 60,000 to covert to minutes
                val selectedDateInMinutes = theDate!!.time / 60000 // !! converts to non-null

                // Show minutes
                val ageInMinutes = findViewById<TextView>(R.id.ageInMinutes)
                ageInMinutes.text = selectedDateInMinutes.toString()
            }, year, month, day
        ).show()
    }
}