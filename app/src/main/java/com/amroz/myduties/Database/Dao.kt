package com.amroz.myduties.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.amroz.myduties.Tasks


@Dao
interface  Dao{
 @Insert
 fun addTask(tasks: Tasks)



 //get tasks
 @Query("SELECT * FROM Tasks where level=:level ORDER BY id DESC ")
 fun getTask(level: Int):LiveData<List<Tasks>>


 @Update
 fun updateTask(tasks: Tasks)

 //update
 @Query("UPDATE Tasks SET level= :level where id= :id ")
 fun updateTaskToInprogress(level: Int,id:Int )




}