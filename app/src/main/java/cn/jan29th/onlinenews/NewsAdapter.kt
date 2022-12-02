package cn.jan29th.onlinenews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(val newsList: List<News>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.newsImage)
        var title: TextView = view.findViewById(R.id.newsTitle)
        var desc: TextView = view.findViewById(R.id.newsDetails)
        var publishTime: TextView = view.findViewById(R.id.newsTime)
        var author:TextView = view.findViewById(R.id.author)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = newsList[position]
        Glide.with(holder.itemView.context).load(news.coverPic).into(holder.image)
        holder.title.text = news.title
        holder.desc.text = news.desc
        holder.publishTime.text = news.pubDate
        holder.author.text=news.source
    }

    override fun getItemCount() = newsList.size
}