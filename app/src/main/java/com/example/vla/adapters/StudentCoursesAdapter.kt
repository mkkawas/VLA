package com.example.vla.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vla.R
import com.example.vla.student.StudentCourseView
import com.example.vla.data.DataStorage
import kotlinx.android.synthetic.main.courses_adapter.view.*

class StudentCoursesAdapter(courses:String): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list: List<String> = courses.split(";").toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.courses_adapter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        when (holder) {

            is ViewHolder -> {
                holder.bind(list[position])
            }

        }
    }

    override fun getItemCount() = list.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(s: String) {



            itemView.course.text = s


            itemView.setOnClickListener {
                val i = Intent(it.context, StudentCourseView::class.java)
                DataStorage.courseSelected = itemView.course.text.toString()
                it.context.startActivity(i)
            }

        }

    }
}