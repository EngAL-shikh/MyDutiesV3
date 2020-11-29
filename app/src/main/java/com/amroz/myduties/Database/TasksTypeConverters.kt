package com.amroz.myduties.Database

import androidx.room.TypeConverter
import java.util.*

class TasksTypeConverters {

    @TypeConverter
    fun  fromDate(date:Date?):Long?{

        return date?.time

    }

    @TypeConverter
    fun toDate(millisSimceEpoch:Long?):Date?{
        return  millisSimceEpoch?.let {
            Date(it)
        }
    }

}