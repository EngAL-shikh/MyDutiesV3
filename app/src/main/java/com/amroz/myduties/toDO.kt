package com.amroz.myduties

import android.app.AlertDialog
import android.app.ProgressDialog.show
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.costumdialog.*
import kotlinx.android.synthetic.main.fragment_to_do.*
import kotlinx.android.synthetic.main.to_do_list.*
import java.io.DataInput
import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*



private lateinit var tasks: Tasks
class toDO : Fragment(),DialogAdd.Callbacks {



    val sdf = SimpleDateFormat("EEE, MMM d, ''yy")
    val currentDate = sdf.format(Date())
    lateinit var add: Button
    lateinit var toprogress: Button
    private lateinit var toDoRecyclerView: RecyclerView
    private var adapter: TaskAdapter? = TaskAdapter(emptyList())
    private val taskViewModel by lazy {
        ViewModelProviders.of(this).get(TaskViewModel::class.java)
    }




    var type:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type=arguments?.getSerializable("type")as String
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view =inflater.inflate(R.layout.fragment_to_do, container, false)
        add=view.findViewById(R.id.adding)







        toDoRecyclerView = view.findViewById(R.id.toDo_rec) as RecyclerView
        toDoRecyclerView.layoutManager = LinearLayoutManager(context)





        add.setOnClickListener {

            DialogAdd().apply {

                setTargetFragment(this@toDO,0)
                show(this@toDO.requireFragmentManager(),"input")
            }
        }



        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (type=="todo"){

            taskViewModel.taskListLiveData .observe(
                viewLifecycleOwner,
                Observer { tasks ->
                    tasks?.let {

                        Log.d("amroz",tasks.size.toString())
                        updateUI(tasks)

                    }
                })
        }else if(type=="done"){

            taskViewModel.taskListLiveDataDone .observe(
                viewLifecycleOwner,
                Observer { tasks ->
                    tasks?.let {

                        Log.d("amroz",tasks.size.toString())
                        updateUI(tasks)

                    }
                })

        }else{

            taskViewModel.taskListLiveDatainprogress .observe(
                viewLifecycleOwner,
                Observer { tasks ->
                    tasks?.let {

                        Log.d("amroz",tasks.size.toString())

                        updateUI(tasks)


                    }
                })

        }

    }
    private inner class TaskHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = itemView.findViewById(R.id.title)
        val det: TextView = itemView.findViewById(R.id.det)
        val date: TextView = itemView.findViewById(R.id.date)
       // val level: TextView = itemView.findViewById(R.id.level)
        val next: ImageView = itemView.findViewById(R.id.next)
        val linearTodo: LinearLayout = itemView.findViewById(R.id.linear_todo)
        val back: ImageView = itemView.findViewById(R.id.back)
        val card: CardView = itemView.findViewById(R.id.card)






        private lateinit var tasks: Tasks
        fun bind(item: Tasks) {
            this.tasks = item
            var databaseDate=sdf.format(tasks.date)
            title.text = this.tasks.title
            det.text = this.tasks.det
            date.text ="the task end in : " +databaseDate
            Log.d("h",databaseDate.toString())
         // get diffrent between tasks days

            var date1=currentDate
            var date2= databaseDate
            val difference: Long = abs(sdf.parse(date1).time - sdf.parse(date2).time)
            val differenceDates = difference / (24 * 60 * 60 * 1000)
            val dayDifference = differenceDates.toString().toInt()

            if (currentDate.equals(databaseDate)){

                linearTodo.setBackgroundResource(R.color.red)
            }else if(dayDifference <=3){

                linearTodo.setBackgroundResource(R.color.orange)


            }







        }

        init {




            // controling the tow button

            if (type=="todo"){
               adding.visibility=View.VISIBLE
                back.visibility=View.GONE
            }else if (type=="done"){
                next.visibility=View.GONE
                linearTodo.setBackgroundResource(R.color.green)



            }else if (type=="inprogress"){

                back.visibility=View.VISIBLE
                next.visibility=View.VISIBLE

            }




            back.setOnClickListener {

                if (type=="inprogress"){
                    val builder= AlertDialog.Builder(requireContext())
                    builder.setPositiveButton("yes"){_,_->

                        taskViewModel.updateTaskToInprogress(tasks,1)
                    }

                    builder.setNegativeButton("no"){_,_->


                    }
                    builder.setTitle("Transfer ${tasks.title}")
                    builder.setMessage("Are you sure")
                    builder.create().show()

                }else{
                    val builder=AlertDialog.Builder(requireContext())
                    builder.setPositiveButton("yes"){_,_->

                        taskViewModel.updateTaskToInprogress(tasks,2)
                    }

                    builder.setNegativeButton("no"){_,_->


                    }
                    builder.setTitle("Transfer ${tasks.title}")
                    builder.setMessage("Are you sure")
                    builder.create().show()

                }


            }

            next.setOnClickListener {


                if (type=="todo"){
                    val builder=AlertDialog.Builder(requireContext())
                    builder.setPositiveButton("yes"){_,_->

                        taskViewModel.updateTaskToInprogress(tasks,2)
                    }

                    builder.setNegativeButton("no"){_,_->


                    }
                    builder.setTitle("Transfer ${tasks.title}")
                    builder.setMessage("Are you sure")
                    builder.create().show()
                }else{

                    val builder=AlertDialog.Builder(requireContext())
                    builder.setPositiveButton("yes"){_,_->

                        taskViewModel.updateTaskToInprogress(tasks,3)

                    }

                    builder.setNegativeButton("no"){_,_->


                    }
                    builder.setTitle("Transfer ${tasks.title}")
                    builder.setMessage("Are you sure")
                    builder.create().show()
                }


            }


        }







    }
    private inner class TaskAdapter(var tasks: List<Tasks>) :
        RecyclerView.Adapter<TaskHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
            val view = layoutInflater.inflate(R.layout.to_do_list, parent, false)
            return TaskHolder(view)
        }



        override fun getItemCount(): Int {
//            if(tasks.size<=0){
//                empty.visibility=View.VISIBLE
//                toDo_rec.visibility=View.GONE
//
//            }else{
//                empty.visibility=View.GONE
//                toDo_rec.visibility=View.VISIBLE
//
//            }


            return tasks.size

        }

        override fun onBindViewHolder(holder: TaskHolder, position: Int) {
            val tasks = tasks[position]
            holder.bind(tasks)



            Log.d("localdate",currentDate.toString())
            Log.d("databasedate",holder.date.text.toString())


        }

    }
    private fun updateUI(tasks: List<Tasks>) {

        adapter = TaskAdapter(tasks)
        toDoRecyclerView.adapter = adapter
    }




    companion object{
        fun newInstance(data:String):toDO{
            val args=Bundle().apply {
                putSerializable("type",data)
            }
            return  toDO().apply {
                arguments=args
            }
        }
    }

    override fun addnewtask(tasks: Tasks) {
  taskViewModel.addtask(tasks)

    }
}
