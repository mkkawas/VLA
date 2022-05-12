package com.example.vla.teacher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vla.R
import com.example.vla.adapters.TasksAdapter
import com.example.vla.data.DataStorage
import com.example.vla.data.GetTasks
import com.example.vla.services.RetrofitClient
import kotlinx.android.synthetic.main.activity_add_task_view.*
import kotlinx.android.synthetic.main.activity_teacher_tasks_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeacherTasksView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_tasks_view)
        supportActionBar?.hide()

        button_task_add.setOnClickListener {
//            Toast.makeText(applicationContext, "success", Toast.LENGTH_SHORT).show()
            val i =  Intent(applicationContext, AddTaskView::class.java)
//            Log.i("hello","Im here")
            startActivity(i)

        }


        RetrofitClient.instance.getTasksStudent(
            DataStorage.id,
            DataStorage.courseSelected,
            DataStorage.UserType
        ).enqueue(object: Callback<GetTasks>{
            override fun onResponse(call: Call<GetTasks>, response: Response<GetTasks>) {
                val error = response.body()!!.error
                if(!error){
                    DataStorage.oldTasks = response.body()!!.tasks
                    rv_tasks.apply{
                        layoutManager = LinearLayoutManager(applicationContext)
                        adapter = TasksAdapter(response.body()!!.tasks)
                    }
                }else{
                    Toast.makeText(applicationContext, response.body()!!.tasks, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<GetTasks>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()            }

        })

        back_btn6.setOnClickListener {
            val i = Intent(applicationContext, TeacherCourseView::class.java)
            startActivity(i)
        }






    }
}