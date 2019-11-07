package com.example.visma.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.visma.R
import com.example.visma.model.Dogs
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dog_item_row.view.*
import timber.log.Timber

class DogRecyclerAdapter(private val imageUrls: List<String>) :
    RecyclerView.Adapter<DogRecyclerAdapter.ImageHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageHolder {
        val inflatedView = parent.inflate(R.layout.dog_item_row)
        return ImageHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return imageUrls.size
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        val url = imageUrls[position]
        holder.bindImage(url)
    }


    class ImageHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var imageUrl: String? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            Timber.d("Clicked on image!")
        }

        fun bindImage(imageUrl: String) {
            this.imageUrl = imageUrl
            Picasso.with(view.context)
                .load(imageUrl)
                .placeholder(R.drawable.progress_bar)
                .into(view.dog_picture)
        }
    }

}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}
