package com.amroz.myduties

import androidx.lifecycle.ViewModel
import com.amroz.myduties.Tasks

class TaskViewModel : ViewModel() {

    private  val taskRepository= TaskRepository.get()
   val taskListLiveData=taskRepository.getTasks()
   val taskListLiveDatainprogress=taskRepository.getTaskInProgress()
   val taskListLiveDataDone=taskRepository.getTaskDone()



    fun updateTaskToInprogress(tasks: Tasks,level:Int){
        taskRepository.updateTaskinprogress(tasks,level)
    }
    fun addtask(tasks: Tasks){
        taskRepository.addnewstask(tasks)
    }







}