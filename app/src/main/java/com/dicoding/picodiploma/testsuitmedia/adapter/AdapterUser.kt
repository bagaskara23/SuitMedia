package com.dicoding.picodiploma.testsuitmedia.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dicoding.picodiploma.testsuitmedia.databinding.ItemUserBinding
import com.dicoding.picodiploma.testsuitmedia.dataclass.ListUserItem

class AdapterUser : PagingDataAdapter<ListUserItem, AdapterUser.MyViewHolder>(DIFF_CALLBACK) {

    private val list = ArrayList<ListUserItem>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }

        holder.itemView.setOnClickListener{
            onItemClickCallback?.onItemClicked(data!!)
            Log.e("Adapter", data!!.toString())
        }
    }

    override fun getItemCount(): Int = list.size

    inner class MyViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ListUserItem) {
            binding.apply {
                Glide.with(itemView)
                    .load(user.avatar)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(ivUser)
                tvName.text = user.first_name
                tvEmail.text = user.email
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ListUserItem)
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<ListUserItem> = object : DiffUtil.ItemCallback<ListUserItem>() {

            override fun areItemsTheSame(oldItem: ListUserItem, newItem: ListUserItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ListUserItem,
                newItem: ListUserItem
            ): Boolean {
                return oldItem.first_name == newItem.first_name
            }
        }
    }
}