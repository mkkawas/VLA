package com.example.vla.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.vla.R
import com.example.vla.adapters.BoardsAdapter
import com.example.vla.data.DataStorage
import com.example.vla.data.GetBoardsModel
import com.example.vla.services.RetrofitClient
import kotlinx.android.synthetic.main.activity_board_view.*
import retrofit2.Callback
import retrofit2.Response

class BoardView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_view)
        supportActionBar?.hide()


        RetrofitClient.instance.getBoards(
            DataStorage.id,
            DataStorage.courseSelected
        ).enqueue(object : Callback<GetBoardsModel> {
            override fun onResponse(
                call: retrofit2.Call<GetBoardsModel>,
                response: Response<GetBoardsModel>
            ) {
                val boards = response.body()!!.boards
                DataStorage.oldBoards = boards
                val error = response.body()!!.error
                val message = response.body()!!.message
               //  Toast.makeText(applicationContext, boards, Toast.LENGTH_SHORT).show()
                if (!error) {
                    rv_boards.apply {
                        layoutManager = GridLayoutManager(applicationContext, 2)
                        adapter = BoardsAdapter(boards)
                    }
                }else{
                    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                    DataStorage.LastBoardId = 999
                }
            }

            override fun onFailure(call: retrofit2.Call<GetBoardsModel>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

        })


        btn_addBoard.setOnClickListener {

            // Toast.makeText(applicationContext, DataStorage.LastBoardId.toString(), Toast.LENGTH_SHORT).show()
            val i = Intent(applicationContext, AddBoardView::class.java)
            startActivity(i)

        }

        back_btn2.setOnClickListener {

            val i = Intent(applicationContext, StudentCourseView::class.java)
            startActivity(i)

        }


    }
}