package com.amroz.myduties

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat
import java.util.*

@Entity
data class Tasks(
    @PrimaryKey(autoGenerate = true) val id:Int,
    var title:String,
    var det:String,
    var date:Date=Date(),
    var level:Int=1) {
}