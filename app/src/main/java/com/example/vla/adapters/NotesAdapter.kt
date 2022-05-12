package com.example.vla.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.vla.R
import com.example.vla.data.DataStorage
import com.example.vla.data.GetBoardModel
import com.example.vla.data.GetNotes
import com.example.vla.services.RetrofitClient
import com.example.vla.student.NotesView
import kotlinx.android.synthetic.main.boards_adapter.view.*
import kotlinx.android.synthetic.main.notes_adapter.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotesAdapter(notes: String) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val list: List<String> = notes.split(";").toList()
    private val lastNotesId = list.last().toInt()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.notes_adapter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {

            is ViewHolder -> {
                //Toast.makeText(holder.itemView.context, boards, Toast.LENGTH_SHORT).show()
                holder.bind(list[position])
                //holder.itemView.board_item.text = list[position]
            }

        }

            DataStorage.LastNoteID = lastNotesId

    }

    override fun getItemCount() = list.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(s: String) {

            RetrofitClient.instance.getNote(
                DataStorage.id,
                DataStorage.boardSelected,
                DataStorage.courseSelected,
                s
            ).enqueue(object : Callback<GetNotes> {
                override fun onResponse(call: Call<GetNotes>, response: Response<GetNotes>) {
                    itemView.notey.text = response.body()!!.desc
                }

                override fun onFailure(call: Call<GetNotes>, t: Throwable) {
                    Toast.makeText(itemView.context, t.message, Toast.LENGTH_SHORT).show()
                }

            })

            itemView.setOnClickListener {
                Toast.makeText(itemView.context, s, Toast.LENGTH_SHORT).show()
            }

        }

    }
}