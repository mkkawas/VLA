package com.example.vla.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.vla.R
import com.example.vla.data.DataStorage
import com.example.vla.data.GetNotes
import com.example.vla.data.GetSessionModel
import com.example.vla.data.GetSessionsModel
import com.example.vla.services.RetrofitClient
import kotlinx.android.synthetic.main.notes_adapter.view.*
import kotlinx.android.synthetic.main.sessions_adapter.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SessionsAdapter(sessions: String) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val list: List<String> = sessions.split(";").toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.sessions_adapter, parent, false)
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

        DataStorage.LastSessionId = list.last().toInt()

    }


    override fun getItemCount() = list.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(s: String) {

            //itemView.sessiony.text= s
            RetrofitClient.instance.getSession(
                DataStorage.id,
                s
            ).enqueue(object : Callback<GetSessionModel> {
                override fun onResponse(
                    call: Call<GetSessionModel>,
                    response: Response<GetSessionModel>
                ) {
                    val day = response.body()!!.day
                    val course = response.body()!!.course
                    val start = response.body()!!.start_time
                    val duration = response.body()!!.duration
                    itemView.txt_coursy.text = course
                    itemView.txt_daysies.text =day
                    itemView.starty.text = start
                    itemView.txt_duration.text = "Duration: $duration"
                }

                override fun onFailure(call: Call<GetSessionModel>, t: Throwable) {
                    Toast.makeText(itemView.context, t.message, Toast.LENGTH_SHORT).show()                }

            })
        }

    }


}