package com.example.vla.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.vla.R
import com.example.vla.adapters.SessionsAdapter
import com.example.vla.data.DataStorage
import com.example.vla.data.GetSessionsModel
import com.example.vla.services.RetrofitClient
import kotlinx.android.synthetic.main.activity_student_home_page.*
import kotlinx.android.synthetic.main.activity_study_sessions_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudySessionsView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study_sessions_view)
        supportActionBar?.hide()


        RetrofitClient.instance.getSessions(
            DataStorage.id
        ).enqueue(object : Callback<GetSessionsModel> {
            override fun onResponse(
                call: Call<GetSessionsModel>,
                response: Response<GetSessionsModel>
            ) {
                val error = response.body()!!.error
                val message = response.body()!!.message
                DataStorage.oldSessions = response.body()!!.sessions
                if(!error){
                    rv_sessions.apply{
                        layoutManager = GridLayoutManager(applicationContext, 1)
                        adapter = SessionsAdapter(DataStorage.oldSessions)
                    }
                }
                //Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<GetSessionsModel>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()            }

        })

        btn_addSession.setOnClickListener {
            val i = Intent(applicationContext, AddSessionView::class.java)
            startActivity(i)
        }
        back_btn4.setOnClickListener {

            val i = Intent(applicationContext, StudentHomePage::class.java)
            startActivity(i)

        }


    }
}