package com.gilangarinata.testprogrammermobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_video.view.*


/**
 * Created by Gilang Arinata on 10/01/21.
 * https://github.com/gilangarinata/
 */
class VideoAdapter(val items : MutableList<VideoModel>) : RecyclerView.Adapter<VideoAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_video,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.itemView.apply {
            Glide.with(this)
                .load(items[position].imageUrl)
                .into(ivVideo)
            title.text = items[position].title
            setOnClickListener {
                onItemClickListener?.let {
                    it(items[position])
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private var onItemClickListener: ((VideoModel) -> Unit)? = null

    fun setOnItemClickListener(listener: (VideoModel) -> Unit) {
        onItemClickListener = listener
    }
}