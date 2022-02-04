package com.mtjin.bungsegwon.views

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mtjin.bungsegwon.model.Menu
import com.mtjin.bungsegwon.views.enroll_detail.EnrollMenuAdapter


@BindingAdapter("urlImage")
fun ImageView.setUrlImage(url: String) {
    Glide.with(this).load(url)
        .thumbnail(0.1f)
        .into(this)
}

@BindingAdapter("setMenus")
fun RecyclerView.setMenuAdapterItems(items: List<Menu>?) {
    items?.let {
        (adapter as EnrollMenuAdapter).submitList(it.toMutableList())
    }
}