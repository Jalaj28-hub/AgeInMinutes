package com.example.dobcalcu

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
   // private var selDate:TextView?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnpicker:Button=findViewById(R.id.btnDatePicker);
        btnpicker.setOnClickListener {
           clickDateListner();
        }
    }
    private fun clickDateListner(){
        val tvselDate:TextView=findViewById(R.id.selDate);
        val tvSelectedDateInMinutes:TextView = findViewById(R.id.ageinmin);
        var myCalendar=Calendar.getInstance();
        var myYear=myCalendar.get(Calendar.YEAR);
        var myMonth=myCalendar.get(Calendar.MONTH);
        var myDayOfMonth=myCalendar.get(Calendar.DAY_OF_MONTH);
        val dpd = DatePickerDialog(this,{ _, selectedYear, selectedMonth, selectedDayOfMonth ->

            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            // Selected date it set to the TextView to make it visible to user.
            tvselDate.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate = sdf.parse(selectedDate)

            theDate?.let {

                val selectedDateInMinutes = theDate.time/60000
                // Here we have parsed the current date with the Date Formatter which is used above.
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                //use the safe call operator with .let to avoid app crashing it currentDate is null
                currentDate?.let {

                    val currentDateToMinutes = currentDate.time/60000

                    val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes
                    tvSelectedDateInMinutes.text = differenceInMinutes.toString()
                }
            }
        },
            myYear,myMonth,myDayOfMonth)

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

//
    }
}
