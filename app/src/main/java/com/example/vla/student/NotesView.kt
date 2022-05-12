package com.example.vla.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.vla.R
import com.example.vla.adapters.NotesAdapter
import com.example.vla.data.DataStorage
import com.example.vla.data.GetNotesFromBoardModel
import com.example.vla.services.RetrofitClient
import kotlinx.android.synthetic.main.activity_notes_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotesView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_view)
        supportActionBar?.hide()
    //    Toast.makeText(applicationContext, DataStorage.boardSelected, Toast.LENGTH_SHORT).show()

        RetrofitClient.instance.getNotesFromBoard(
            DataStorage.id,
            DataStorage.boardSelected,
            DataStorage.courseSelected
        ).enqueue(object : Callback<GetNotesFromBoardModel> {
            override fun onResponse(
                call: Call<GetNotesFromBoardModel>,
                response: Response<GetNotesFromBoardModel>
            ) {
                val error = response.body()!!.error
                val message = response.body()!!.message
                DataStorage.oldNotes = response.body()!!.notes
                if(!error){
//                    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
//                    Toast.makeText(applicationContext, DataStorage.oldNotes, Toast.LENGTH_SHORT).show()
                    rv_notes.apply{
                        layoutManager = GridLayoutManager(applicationContext, 2)
                        adapter = NotesAdapter(DataStorage.oldNotes)
                    }

                }else{
                    DataStorage.oldNotes = ""
                    DataStorage.LastNoteID = 999
                }

            }

            override fun onFailure(call: Call<GetNotesFromBoardModel>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()            }

        })

        btn_addNote.setOnClickListener {
            val i = Intent(applicationContext, AddNoteView::class.java)
            startActivity(i)
        }

        back_btn.setOnClickListener {
            val i = Intent(applicationContext, BoardView::class.java)
            startActivity(i)
        }


    }
}