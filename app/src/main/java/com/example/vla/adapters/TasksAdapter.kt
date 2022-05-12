package com.example.vla.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.vla.R
import com.example.vla.data.DataStorage
import com.example.vla.data.FetchTask
import com.example.vla.services.RetrofitClient
import kotlinx.android.synthetic.main.tasks_adapter.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TasksAdapter(tasks: String) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list: List<String> = tasks.split(";").toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.tasks_adapter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        when (holder) {

            is ViewHolder -> {
                holder.bind(list[position], position)
            }

        }
        DataStorage.lastTaskId = list.last().toInt()
    }

    override fun getItemCount() = list.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(s: String, i: Int) {
            val teacherId: String = if (DataStorage.UserType == "student") {
                DataStorage.teacherId
            } else {
                DataStorage.id
            }

            RetrofitClient.instance.getTask(s, DataStorage.courseSelected, teacherId)
                .enqueue(object : Callback<FetchTask> {
                    override fun onResponse(
                        call: Call<FetchTask>,
                        response: Response<FetchTask>
                    ) {
                        itemView.taskadapter.text = "Task ${i + 1}: " + response.body()!!.desc
                    }

                    override fun onFailure(call: Call<FetchTask>, t: Throwable) {
                        Toast.makeText(itemView.context, t.message, Toast.LENGTH_SHORT).show()
                    }

                })


            itemView.setOnClickListener {
                // val i = Intent(it.context, StudentCourseView::class.java)
                // DataStorage.courseSelected = itemView.course.text.toString().trim()
                // it.context.startActivity(i)
            }

        }

    }
}