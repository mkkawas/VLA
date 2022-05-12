package com.example.vla.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.vla.R
import com.example.vla.data.DataStorage
import com.example.vla.data.FetchTeacherName
import com.example.vla.services.RetrofitClient
import kotlinx.android.synthetic.main.activity_student_course_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class StudentCourseView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_course_view)
        supportActionBar?.hide()

        course_name.text = DataStorage.courseSelected

        try{
        RetrofitClient.instance.getName(
            DataStorage.courseSelected,
            DataStorage.id
        ).enqueue(object: Callback<FetchTeacherName>{
            override fun onResponse(
                call: Call<FetchTeacherName>,
                response: Response<FetchTeacherName>
            ) {
                val error = response.body()!!.error
                val name = response.body()!!.name
                if(!error){
                    teacher_name_SV.text = "Instructor: "+name
                    DataStorage.teacherId = response.body()!!.teacher_id
                }
            }

            override fun onFailure(call: Call<FetchTeacherName>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })}catch (e:Exception){
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
        }



        student_tasks_btn.setOnClickListener {
            val i  = Intent(applicationContext, StudentTasksView::class.java)
            startActivity(i)
        }

        bulletin_board_btn.setOnClickListener {
            val i = Intent(applicationContext, BoardView::class.java)
            startActivity(i)
        }
        back_btn3.setOnClickListener {
            val i = Intent(applicationContext, StudentHomePage::class.java)
            startActivity(i)
        }

    }
}