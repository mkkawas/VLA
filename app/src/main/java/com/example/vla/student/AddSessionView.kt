package com.example.vla.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.vla.R
import com.example.vla.data.AddSessionModel
import com.example.vla.data.DataStorage
import com.example.vla.data.UpdateSessionsModel
import com.example.vla.services.RetrofitClient
import kotlinx.android.synthetic.main.activity_add_session_view.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddSessionView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_session_view)




        btn_add_session.setOnClickListener {

            val newId = DataStorage.LastSessionId + 1
            val id = newId.toString()

            val day = et_day.text.toString().trim()
            val start = et_start.text.toString().trim()
            val duration = et_duration.text.toString().trim()
            val course = et_course.text.toString().trim()


            if (day.isEmpty()) {
                et_day.error = "day required"
                return@setOnClickListener
            }

            if (start.isEmpty()) {
                et_start.error = "start time required"
                return@setOnClickListener
            }

            if (duration.isEmpty()) {
                et_duration.error = "duration required"
                return@setOnClickListener
            }

            if (course.isEmpty()) {
                et_course.error = "course required"
                return@setOnClickListener
            }

            RetrofitClient.instance.addSession(
                DataStorage.id,
                id,
                day,
                start,
                duration,
                course
            ).enqueue(object : Callback<AddSessionModel> {
                override fun onResponse(
                    call: Call<AddSessionModel>,
                    response: Response<AddSessionModel>
                ) {
                    val error = response.body()!!.error
                    val message = response.body()!!.message
                    if (!error) {
                        // Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                        RetrofitClient.instance.updateSessions(
                            DataStorage.id,
                            DataStorage.oldSessions,
                            id
                        ).enqueue(object : Callback<UpdateSessionsModel> {
                            override fun onResponse(
                                call: Call<UpdateSessionsModel>,
                                response: Response<UpdateSessionsModel>
                            ) {
                                Toast.makeText(
                                    applicationContext,
                                    response.body()!!.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                                val i = Intent(applicationContext, StudySessionsView::class.java)
                                startActivity(i)
                            }

                            override fun onFailure(call: Call<UpdateSessionsModel>, t: Throwable) {
                                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT)
                                    .show()
                            }

                        })

                    }
                }

                override fun onFailure(call: Call<AddSessionModel>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                }

            })


        }
    }
}