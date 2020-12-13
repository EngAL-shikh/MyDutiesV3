package com.amroz.myduties

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.costumdialog.*

import java.text.SimpleDateFormat
import java.util.*


class DialogAdd: DialogFragment(),DatePickerFragment.Callbacks {

    val sdf = SimpleDateFormat("EEE MMM d HH mm ss z yyyy")

    private lateinit var tasks: Tasks


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        tasks= Tasks(0,"","", Date())
        val v=activity?.layoutInflater?.inflate(R.layout.costumdialog,null)

        val title=v?.findViewById(R.id.ed_title) as EditText
        val det= v.findViewById(R.id.ed_det) as EditText
        val ed_date=v.findViewById(R.id.ed_date) as Button



        ed_date.setOnClickListener {
            DatePickerFragment().apply {
                setTargetFragment(this@DialogAdd,0)
                show(this@DialogAdd.requireFragmentManager(), "input")
            }
        }

        return  AlertDialog.Builder(requireContext(),R.style.ThemeOverlay_AppCompat_Dialog_Alert)
            .setView(v)
            .setPositiveButton("ADD"){dialog,_->
                val data=Tasks(0,title.text.toString(),
                    det.text.toString()
                    , tasks.date ,
                   1
                )
                targetFragment.let {fragment ->
                    (fragment as Callbacks).addnewtask(data)
                }
            }.setNegativeButton("cancel"){dialog,_->
                dialog.cancel()
            }.create()




    }





    override fun onDateSelected(date: Date) {

           tasks.date = date
           Log.d("date",date.toString())
    }
    interface Callbacks{

        fun addnewtask(tasks: Tasks)

    }


}