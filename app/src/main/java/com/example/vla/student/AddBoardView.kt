package com.example.vla.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.vla.R
import com.example.vla.data.AddBoardModel
import com.example.vla.data.DataStorage
import com.example.vla.data.UpdateBoardsModel
import com.example.vla.services.RetrofitClient
import kotlinx.android.synthetic.main.activity_add_board_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddBoardView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_board_view)
        tv_course.text = DataStorage.courseSelected
        val newBoardId = DataStorage.LastBoardId + 1
        val newBoard = newBoardId.toString()
        // Toast.makeText(applicationContext, newBoardId.toString(), Toast.LENGTH_SHORT).show()

        addButton.setOnClickListener {


            if (et_title.text.isEmpty()) {
                et_title.error = "password required"
                return@setOnClickListener
            }
            val title = et_title.text.toString().trim()

            // Toast.makeText(applicationContext, newBoard, Toast.LENGTH_SHORT).show()

            RetrofitClient.instance.addBoard(

                newBoard,
                DataStorage.id,
                title,
                DataStorage.courseSelected

            ).enqueue(object : Callback<AddBoardModel> { //add board to Boards table in DB
                override fun onResponse(
                    call: Call<AddBoardModel>,
                    response: Response<AddBoardModel>
                ) {
                    val error = response.body()!!.error
                    val message = response.body()!!.message
                    if(!error){
                        RetrofitClient.instance.updateBoards(
                            DataStorage.id,
                            DataStorage.courseSelected,
                            DataStorage.oldBoards,
                            newBoard
                        ).enqueue(object: Callback<UpdateBoardsModel>{
                            override fun onResponse(
                                call: Call<UpdateBoardsModel>,
                                response: Response<UpdateBoardsModel>
                            ) {
                                val update = response.body()!!.message
                                Toast.makeText(applicationContext, update, Toast.LENGTH_SHORT).show()
                                val i = Intent(applicationContext, BoardView::class.java)
                                startActivity(i)
                            }

                            override fun onFailure(call: Call<UpdateBoardsModel>, t: Throwable) {
                                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                            }

                        })
                    } else {
                        et_title.error = message
                    }
                }
                override fun onFailure(call: Call<AddBoardModel>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                }

            })


        }
    }
}