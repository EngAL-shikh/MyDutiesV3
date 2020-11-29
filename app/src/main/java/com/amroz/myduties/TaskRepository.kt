package com.amroz.myduties

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.amroz.myduties.Database.TasksDatabase
import com.amroz.myduties.Database.migration
import com.amroz.myduties.Tasks

import java.util.concurrent.Executors

private const val DATABASE_NAME = "tasks_database"
class TaskRepository private constructor(context: Context){
    private  val database:TasksDatabase= Room.databaseBuilder(
        context.applicationContext,TasksDatabase::class.java,
        DATABASE_NAME
    ).addMigrations(migration).build()

    private  val taskDao=database.TasksDao()
    private  var executor = Executors.newSingleThreadExecutor()
    //private  val filesDir =context.applicationContext.filesDir


     // add new tasks
    fun addnewstask(tasks: Tasks){
         executor.execute{
             taskDao.addTask(tasks)
         }
     }



    // update tasks
    fun updateTask(tasks: Tasks) {
        executor.execute {
            taskDao.updateTask(tasks)
        }
    }

    // select  all student

    fun getTasks(): LiveData<List<Tasks>> = taskDao.getTask()
    fun getTaskInProgress(): LiveData<List<Tasks>> = taskDao.getTaskInProgress()
    fun getTaskDone(): LiveData<List<Tasks>> = taskDao.getTaskDone()




    fun updateTaskinprogress(tasks: Tasks,level:Int) {
        executor.execute {
            taskDao.updateTaskToInprogress(level,tasks.id)
        }
    }

    companion object {
        private var INSTANCE: TaskRepository? = null
        fun initialize(context: Context)
        {
            if (INSTANCE == null)
        {      INSTANCE = TaskRepository(context)
        }
        }
        fun get():
                TaskRepository {
            return INSTANCE ?:
            throw IllegalStateException("CrimeRepository must be initialized")
        }
    }
}