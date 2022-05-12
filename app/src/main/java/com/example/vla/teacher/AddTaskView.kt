package com.example.vla.teacher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.vla.R
import com.example.vla.data.AddTaskModel
import com.example.vla.data.DataStorage
import com.example.vla.data.UpdateTasksModel
import com.example.vla.services.RetrofitClient
import kotlinx.android.synthetic.main.activity_add_task_view.*
import kotlinx.android.synthetic.main.activity_teacher_tasks_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field

class AddTaskView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task_view)
        supportActionBar?.hide()

        tv_course3.text = DataStorage.courseSelected

        val oldTasks = DataStorage.oldTasks

        val newTaskId = DataStorage.lastTaskId + 1


        taskAdd_btn.setOnClickListener {

            if (et_task.text.isEmpty()) {
                et_task.error = "Task Required"
                return@setOnClickListener
            }
            val desc = et_task.text.toString().trim()


            RetrofitClient.instance.addTask(
                DataStorage.courseSelected,
                desc,
                DataStorage.id,
                newTaskId.toString()
            ).enqueue(object : Callback<AddTaskModel> {
                override fun onResponse(
                    call: Call<AddTaskModel>,
                    response: Response<AddTaskModel>
                ) {
                    val error = response.body()!!.error

                    if(!error){
                        RetrofitClient.instance.updateTasks(
                            DataStorage.id,
                            oldTasks,
                            DataStorage.courseSelected,
                            newTaskId.toString()
                        ).enqueue(object: Callback<UpdateTasksModel>{
                            override fun onResponse(
                                call: Call<UpdateTasksModel>,
                                response: Response<UpdateTasksModel>
                            ) {
                                val message = response.body()!!.message
                                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                                val i = Intent(applicationContext, TeacherTasksView::class.java)
                                startActivity(i)
                            }

                            override fun onFailure(call: Call<UpdateTasksModel>, t: Throwable) {
                                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                            }

                        })
                    }
                }

                override fun onFailure(call: Call<AddTaskModel>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                }

            })

            back_btn5.setOnClickListener {
                val i = Intent(applicationContext, TeacherTasksView::class.java)
                startActivity(i)
            }



        }
    }
}