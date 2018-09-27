package com.cogitator.adfreenews.view.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cogitator.adfreenews.model.News
import com.cogitator.adfreenews.utils.inflate
import kotlinx.android.synthetic.main.news_list_item.view.*

/**
 * @author Ankit Kumar on 27/09/2018
 */

class NewsListAdapter(var newsList: ArrayList<News>, val onNewsItemClick: OnNewsItemClick) : RecyclerView.Adapter<NewsListAdapter.NewsItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder = NewsItemViewHolder(parent.inflate(R.layout.news_list_item))

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val newsItem = newsList[holder.adapterPosition]
        showCategoryImage(holder.itemView.newsIv, newsItem.urlToImage
                ?: "", holder.itemView.context)
        holder.itemView.newsHeadingTv.maxLines = if (newsItem.description.isNullOrEmpty()) 3 else 2
        holder.itemView.newsHeadingTv.text = newsItem.title ?: ""
        holder.itemView.newsDescTv.text = newsItem.description ?: ""
        holder.itemView.newsSourceTv.text = newsItem.source?.name ?: ""
        holder.itemView.bookmarkIv.setImageDrawable(ContextCompat.getDrawable(holder.itemView.context, if (newsItem.isBookmarked) R.drawable.ic_bookmark else R.drawable.ic_bookmark_border))
        holder.itemView.bookmarkIv.setOnClickListener {
            newsItem.isBookmarked = !newsItem.isBookmarked
            onNewsItemClick.onBookmarkIvClick(newsItem, holder.adapterPosition)
        }
        holder.itemView.cardView.setOnClickListener {
            onNewsItemClick.onItemClick(newsItem.id)
        }

    }

    fun setData(newsList: ArrayList<News>) {
        this.newsList = newsList
        notifyDataSetChanged()
    }

    fun refreshBookmarkStatus(newsId: String) {
        val position = newsList.indexOfFirst { it.id == newsId }
        val news = newsList[position]
        newsList.set(position, news)
        notifyItemChanged(position)
    }

    fun removeNewsItem(position: Int) {
        newsList.removeAt(position)
        notifyItemRemoved(position)
        if (newsList.isEmpty())
            onNewsItemClick.onNewsListEmpty()
    }

    override fun getItemCount(): Int = newsList.size

    fun showCategoryImage(newsIv: ImageView, imageUrl: String, context: Context) {
        try {

            Glide.with(context)
                    .load(imageUrl)
                    .apply(RequestOptions()
                            .placeholder(R.drawable.ic_placeholder)
                            .error(R.drawable.ic_placeholder))
                    .into(newsIv)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    class NewsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

interface OnNewsItemClick {
    fun onBookmarkIvClick(news: News, position: Int)
    fun onItemClick(newsId: String)
    fun onNewsListEmpty()
}