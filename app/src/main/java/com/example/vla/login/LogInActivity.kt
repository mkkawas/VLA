package com.example.vla.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.vla.R
import com.example.vla.services.RetrofitClient
import com.example.vla.data.DataStorage
import com.example.vla.data.LogInModel
import com.example.vla.student.StudentHomePage
import com.example.vla.teacher.TeacherHomePage
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        login.setOnClickListener {

            val email = et_email.text.toString().trim()
            val password = et_password.text.toString().trim()

            if(email.isEmpty()){
                et_email.error = "email required"
                return@setOnClickListener
            }

            if(password.isEmpty()){
                et_password.error = "password required"
                return@setOnClickListener
            }

            try {

                RetrofitClient.instance.logIn(email, password).enqueue(
                    object : Callback<LogInModel> {
                        override fun onResponse(
                            call: Call<LogInModel>,
                            response: Response<LogInModel>
                        ) {
                            val error = response.body()!!.error
                           // val message = response.body()!!.message

                            if(!error){

                                DataStorage.UserType = response.body()!!.user.type
                                DataStorage.id = response.body()!!.user.id
                                DataStorage.name = response.body()!!.user.name

                                if(DataStorage.UserType == "student"){
                                   // Toast.makeText(applicationContext,message, Toast.LENGTH_SHORT).show()
                                    val i = Intent(applicationContext, StudentHomePage::class.java)
                                    startActivity(i)
                                }else{
                                    val i = Intent(applicationContext, TeacherHomePage::class.java)
                                    startActivity(i)
                                }
                            }
                        }
                        override fun onFailure(call: Call<LogInModel>, t: Throwable) {
                            Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                        }
                    })

            }catch(i:Exception){
                Toast.makeText(applicationContext, i.message, Toast.LENGTH_SHORT).show()
            }
//            val i = Intent(applicationContext, Homepage::class.java)
//            startActivity(i)
          //  DataStorage.UserType = response.body()!!.type
          //  DataStorage.id = response.body()!!.id


        }
    }
}