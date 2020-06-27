package com.tsyanuri.android.findmyage

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view)
        }
    }

    fun clickDatePicker(view : View){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selecteddayOfMonth ->
             /*   Toast.makeText(this,
                    "The choosen year is $selectedYear, the month is $selectedMonth and the day is $selecteddayOfMonth",
                    Toast.LENGTH_LONG).show()*/

                val selectedDate = "$selecteddayOfMonth/${selectedMonth+1}/$selectedYear"

                tvSelectDate.setText(selectedDate)

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                //minutes
                val selectedDateToMinutes = theDate!!.time / 60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                // Current date in to minutes.
                val currentDateToMinutes = currentDate!!.time / 60000

                val differenceInMinutes = currentDateToMinutes - selectedDateToMinutes

                tvSelectDateInMinutes.text = differenceInMinutes.toString()

                //days
                val differenceInDays = differenceInMinutes/(60*24)
                tvSelectDateInDays.text = differenceInDays.toString()

                //years
                val differenceInYear = differenceInDays/365
                tvSelectDateInYears.text = differenceInYear.toString()

        },
            year,
            month,
            day)

        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }

}
