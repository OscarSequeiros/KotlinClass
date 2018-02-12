package com.osequeiros.class1

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.view_media_item.view.*
import kotlinx.android.synthetic.main.view_media_item_2.view.*
import kotlin.properties.Delegates

/**
 * Created by osequeiros on 6/08/17.
 * Adaptador del recycler
 */

data class MediaItem(val id: Int, val title: String, val url: String, val type: Type) {
    enum class Type { PHOTO, VIDEO }
}

class MediaAdapter(data: List<MediaItem> = emptyList(), val listener: (MediaItem) -> Unit)
    : RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    var items: List<MediaItem> by Delegates.observable(data) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): ViewHolder {
        val v = parent.inflate(R.layout.view_media_item_2)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(items[position])
        holder.itemView.setOnClickListener { listener(item) }
    }

    override fun getItemCount() = items.size

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        fun bind(item : MediaItem) = with(itemView) {
            text_title.text = item.title
            media_thumb.loadUrl(item.url)
            media_video_indicator.visibility = when (item.type) {
                MediaItem.Type.PHOTO -> View.VISIBLE
                MediaItem.Type.VIDEO -> View.GONE
            }
        }
    }
}