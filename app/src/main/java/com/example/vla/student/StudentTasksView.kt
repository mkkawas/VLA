package com.example.vla.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vla.R
import com.example.vla.adapters.TasksAdapter
import com.example.vla.data.DataStorage
import com.example.vla.data.GetTasks
import com.example.vla.services.RetrofitClient
import kotlinx.android.synthetic.main.activity_student_tasks_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentTasksView : AppCompatActivity() {

    lateinit var taskAdapter: TasksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_tasks_view)
        supportActionBar?.hide()


        RetrofitClient.instance.getTasksStudent(
            DataStorage.id,
            DataStorage.courseSelected,
            DataStorage.UserType
        )
            .enqueue(object: Callback<GetTasks>{
                override fun onResponse(
                    call: Call<GetTasks>,
                    response: Response<GetTasks>
                ) {
                    val error = response.body()!!.error
                    val ids = response.body()!!.tasks

                    if(!error) {
//                        Toast.makeText(applicationContext, ids, Toast.LENGTH_SHORT).show()

                    rv_tasks.apply {

                            layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
                            taskAdapter = TasksAdapter(ids)
                            adapter = taskAdapter


                    }
                    }else{
                        Toast.makeText(applicationContext, ids, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<GetTasks>, t: Throwable) {
//                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

            })



    }
}