package com.amroz.myduties.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.amroz.myduties.Tasks


@Dao
interface  Dao{
 @Insert
 fun addTask(tasks: Tasks)

 //get tasks in to DO
 @Query("SELECT * FROM Tasks where level=1 ORDER BY id DESC ")
 fun getTask():LiveData<List<Tasks>>

 //get the tasks in  Inprogress
 @Query("SELECT * FROM Tasks where level=2 ORDER BY id DESC ")
 fun getTaskInProgress():LiveData<List<Tasks>>

 //get the tasks in  Done
 @Query("SELECT * FROM Tasks where level=3  ORDER BY id DESC")
 fun getTaskDone():LiveData<List<Tasks>>

 @Update
 fun updateTask(tasks: Tasks)

 //update
 @Query("UPDATE Tasks SET level= :level where id= :id ")
 fun updateTaskToInprogress(level: Int,id:Int )

 @Delete
 fun deleteTask(tasks: Tasks)


}