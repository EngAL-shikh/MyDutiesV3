package com.amroz.myduties

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.text.SimpleDateFormat
import java.util.*
private const val ARG_DATE = "date"

class DatePickerFragment:DialogFragment() {



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dateListener = DatePickerDialog.OnDateSetListener {
                _: DatePicker, year: Int, month: Int, day: Int ->
            val resultDate : Date = GregorianCalendar(year, month, day).time
            targetFragment?.let { fragment ->
                (fragment as Callbacks).onDateSelected(resultDate)
            }
        }






       var calendar=Calendar.getInstance()



        var year= calendar.get(Calendar.YEAR)
        var month=  calendar.get(Calendar.MONTH)
        var day= calendar.get(Calendar.DAY_OF_WEEK)

        //return date
        return  DatePickerDialog(requireContext(),dateListener,year,month,day)
    }


    interface Callbacks {

        fun onDateSelected(date: Date)

    }
}