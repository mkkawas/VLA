package com.example.vla.teacher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.vla.R
import com.example.vla.adapters.TeacherCoursesAdapter
import com.example.vla.data.DataStorage
import com.example.vla.data.FetchCourseModel
import com.example.vla.login.LogInActivity
import com.example.vla.services.RetrofitClient
import kotlinx.android.synthetic.main.activity_teacher_home_page.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeacherHomePage : AppCompatActivity() {
    private lateinit var courseAdapter: TeacherCoursesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_home_page)
        supportActionBar?.hide()

        teacher_name.text = "Welcome "+DataStorage.name

        RetrofitClient.instance.getCourses(
            DataStorage.UserType,
            DataStorage.id
        ).enqueue(object: Callback<FetchCourseModel> {
            override fun onResponse(
                call: Call<FetchCourseModel>,
                response: Response<FetchCourseModel>
            ) {
                val error = response.body()!!.error
                //val message = response.body()!!.message
                val courses = response.body()!!.courses
                if(!error){
                    //Toast.makeText(applicationContext,message, Toast.LENGTH_SHORT).show()
                    taught_courses.apply {
                        layoutManager = GridLayoutManager(applicationContext,2)
                        courseAdapter = TeacherCoursesAdapter(courses)
                        adapter = courseAdapter
                    }
                }
            }

            override fun onFailure(call: Call<FetchCourseModel>, t: Throwable) {
                Toast.makeText( applicationContext,t.message, Toast.LENGTH_SHORT).show()
            }

        })

        logout_btn.setOnClickListener {
            DataStorage.reset()
            val i = Intent(applicationContext, LogInActivity::class.java)
            startActivity(i)
        }

    }


}