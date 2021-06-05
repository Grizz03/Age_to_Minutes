package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
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
        }
    }

    // Date Picker function
    fun clickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this,
            { _,
              selectedYear,
              selectedMonth,
              selectedDayOfMonth ->
                // Format for date
                val userSelectedDate = "${selectedMonth+1}/${selectedDayOfMonth}/$selectedYear"
                // Create variable for text view
                val mySelectedDate = findViewById<TextView>(R.id.selectedDate)
                // Setting TextView to userSelectedDate string
                mySelectedDate.text = userSelectedDate

                // Parsing date
                val sdf = SimpleDateFormat("dd/MM/yyy", Locale.ENGLISH)
                val theDate = sdf.parse(userSelectedDate)

                // Converts date to milliseconds, divide by 60,000 to covert to minutes
                val selectedDateInMinutes = theDate!!.time / 60000 // !! converts to non-null
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateToMinutes = currentDate!!.time / 60000
                val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes
                // Convert to age in days
                val days = differenceInMinutes / (60 * 24)
                // alter textViews
                val ageInMinutes = findViewById<TextView>(R.id.ageInMinutes)
                val ageInDays = findViewById<TextView>(R.id.ageInDays)
                ageInMinutes.text = differenceInMinutes.toString()
                ageInDays.text = days.toString()
            }, year, month, day).show()
    }
}