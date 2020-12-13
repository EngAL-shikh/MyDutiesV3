package com.amroz.myduties

import androidx.lifecycle.ViewModel
import com.amroz.myduties.Tasks

class TaskViewModel : ViewModel() {



    private  val taskRepository= TaskRepository.get()
   val taskListLiveData=taskRepository.getTasksTOdo()
   val taskListLiveDatainprogress=taskRepository.getTasksInProgress()
   val taskListLiveDataDone=taskRepository.getTasksDone()



    fun updateTaskToInprogress(tasks: Tasks,level:Int){
        taskRepository.updateTaskinprogress(tasks,level)
    }
    fun addtask(tasks: Tasks){
        taskRepository.addnewstask(tasks)
    }







}