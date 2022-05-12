package com.example.vla.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import com.example.vla.R
import com.example.vla.data.AddNoteModel
import com.example.vla.data.DataStorage
import com.example.vla.data.UpdateNotesModel
import com.example.vla.services.RetrofitClient
import kotlinx.android.synthetic.main.activity_add_note_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddNoteView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note_view)
        supportActionBar?.hide()

        val newNoteId = DataStorage.LastNoteID + 1
        val newNote = newNoteId.toString()


        //Toast.makeText(applicationContext, newNote, Toast.LENGTH_SHORT).show()

        tv_course3.text = DataStorage.courseSelected

        addNote_btn.setOnClickListener {

            if (et_desc.text.isEmpty()) {
                et_desc.error = "note desc required"
                return@setOnClickListener
            }
            val desc = et_desc.text.toString().trim()



                RetrofitClient.instance.addNote(
                    newNote,
                    desc,
                    DataStorage.id,
                    DataStorage.boardSelected,
                    DataStorage.courseSelected
                ).enqueue(object : Callback<AddNoteModel> {
                    override fun onResponse(
                        call: Call<AddNoteModel>,
                        response: Response<AddNoteModel>
                    ) {
                        val error = response.body()!!.error
                        // val message = response.body()!!.message
                        //Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                        if (!error) {

                            RetrofitClient.instance.updateNotes(
                                DataStorage.id,
                                DataStorage.courseSelected,
                                DataStorage.boardSelected,
                                DataStorage.oldNotes,
                                newNote
                            ).enqueue(object : Callback<UpdateNotesModel> {
                                override fun onResponse(
                                    call: Call<UpdateNotesModel>,
                                    response: Response<UpdateNotesModel>
                                ) {
                                    // val newnotes = response.body()!!.newNotes
                                    //   Toast.makeText(applicationContext, newnotes, Toast.LENGTH_SHORT).show()
                                    val i = Intent(applicationContext, NotesView::class.java)
                                    startActivity(i)

                                }

                                override fun onFailure(call: Call<UpdateNotesModel>, t: Throwable) {
                                    Toast.makeText(
                                        applicationContext,
                                        t.message,
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }

                            })


                        }

                    }

                    override fun onFailure(call: Call<AddNoteModel>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                    }

                })

        }


    }
}