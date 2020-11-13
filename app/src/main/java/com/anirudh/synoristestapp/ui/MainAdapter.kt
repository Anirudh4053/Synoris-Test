package com.anirudh.synoristestapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anirudh.synoristestapp.R
import com.anirudh.synoristestapp.data.db.entities.LocalDocData
import com.anirudh.synoristestapp.data.network.DocData
import com.anirudh.synoristestapp.data.network.ResponseData
import com.anirudh.synoristestapp.data.others.changeDateFormat
import com.anirudh.synoristestapp.data.others.hide
import com.anirudh.synoristestapp.data.others.show
import kotlinx.android.synthetic.main.custom_item.view.*

class MainAdapter(private var context: Context, private var orderList: List<LocalDocData>,
                  private var onItemClick:(item: LocalDocData)->Unit = {}): RecyclerView.Adapter<MainAdapter.CommentsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsHolder{
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.custom_item, parent,
                false)
        return CommentsHolder(view)
    }

    override fun getItemCount(): Int = orderList.size

    override fun onBindViewHolder(holder: CommentsHolder, position: Int) {
        val item = orderList[position]
        holder.itemView.idTV.text = "${item.id}"
        holder.itemView.dateTV.text = "${changeDateFormat(item.publicationDate)}"
        holder.itemView.typeTV.text = "${item.articleType}"
        if(!item.abstractValue.isNullOrEmpty()) {
            holder.itemView.abstractTV.show()
            holder.itemView.abstractTV.text = "${item.abstractValue}"
        }
        else
            holder.itemView.abstractTV.hide()

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }

    }

    class CommentsHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}