package com.example.visma.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.visma.R
import com.stfalcon.imageviewer.StfalconImageViewer
import kotlinx.android.synthetic.main.dog_item_row.view.*

class DogRecyclerAdapter() :
    RecyclerView.Adapter<DogRecyclerAdapter.ImageHolder>() {

    val imageUrls  = mutableListOf<String>()

    fun refreshList(newImageUrls: List<String>) {
        imageUrls.clear()
        imageUrls.addAll(newImageUrls)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val inflatedView = parent.inflate(R.layout.dog_item_row)
        return ImageHolder(inflatedView, imageUrls)
    }

    override fun getItemCount(): Int {
        return imageUrls.size
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        val url = imageUrls[position]
        holder.bindImage(url)
    }


    class ImageHolder(v: View, val imageUrls: List<String>) : RecyclerView.ViewHolder(v),
        View.OnClickListener {
        private var view: View = v
        private var imageUrl: String? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            StfalconImageViewer.Builder<String>(view.context, imageUrls, ::loadImage)
                .withStartPosition(imageUrls.indexOf(imageUrl))
                .show()
        }

        private fun loadImage(imageView: ImageView, url: String) {
            imageView.apply {
                Glide.with(view.context)
                    .load(url)
                    .into(imageView)
            }
        }

        fun bindImage(imageUrl: String) {
            this.imageUrl = imageUrl
            Glide.with(view.context)
                .load(imageUrl)
                .placeholder(R.drawable.progress_bar)
                .into(view.dog_picture)
        }
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}
