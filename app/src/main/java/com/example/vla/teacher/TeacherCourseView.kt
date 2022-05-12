package com.example.vla.teacher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vla.R
import com.example.vla.data.DataStorage
import kotlinx.android.synthetic.main.activity_teacher_course_view.*

class TeacherCourseView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_course_view)
        supportActionBar?.hide()

        course.text = DataStorage.courseSelected

        btn_tasks.setOnClickListener {

            val i  = Intent(applicationContext,TeacherTasksView::class.java)
            startActivity(i)

        }
        back_btn7.setOnClickListener {
            val i = Intent(applicationContext, TeacherHomePage::class.java)
            startActivity(i)
        }

    }
}