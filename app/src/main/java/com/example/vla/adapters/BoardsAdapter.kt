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
import com.example.vla.services.RetrofitClient
import com.example.vla.student.NotesView
import kotlinx.android.synthetic.main.boards_adapter.view.*
import kotlinx.android.synthetic.main.courses_adapter.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BoardsAdapter(boards:String): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val list:List<String> = boards.split(";").toList()
    val lastBoardId = list.last().toInt()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.boards_adapter, parent, false)
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

        DataStorage.LastBoardId = lastBoardId
    }

    override fun getItemCount() = list.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(s: String) {


            RetrofitClient.instance.getBoard(
                DataStorage.id,
                s,
                DataStorage.courseSelected
            ).enqueue(object: Callback<GetBoardModel>{
                override fun onResponse(
                    call: Call<GetBoardModel>,
                    response: Response<GetBoardModel>
                ) {
                    val title = response.body()!!.title
                    itemView.boardy.text = title
                }

                override fun onFailure(call: Call<GetBoardModel>, t: Throwable) {
                    Toast.makeText(itemView.context, t.message, Toast.LENGTH_SHORT).show()                }

            })




          itemView.setOnClickListener {
              DataStorage.boardSelected = s
                val i = Intent(it.context, NotesView::class.java)
            //  DataStorage. = itemView.boardy.text.toString()
                it.context.startActivity(i)
             // Toast.makeText(itemView.context, itemView.boardy.text.toString(), Toast.LENGTH_SHORT).show()
         }

        }

    }
}