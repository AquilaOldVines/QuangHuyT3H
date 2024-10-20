package com.example.chitieucanhan.adapter.settingadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chitieucanhan.databinding.ItemImgViewBinding

class HinhDanhMucAdapter(val onItemClick : (Int) -> Unit) : ListAdapter<Int, HinhDanhMucAdapter.ChonImgHolder>(

    object : DiffUtil.ItemCallback<Int>(){

        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {

            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {

            return oldItem == newItem
        }
    }
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChonImgHolder {

        return ChonImgHolder(

            ItemImgViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ChonImgHolder, position: Int) {

        holder.bind(getItem(position))
    }

    inner class ChonImgHolder(val binding: ItemImgViewBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(img : Int){

            with(binding){

                imgHinhDanhMuc.setImageResource(img)
                imgHinhDanhMuc.setOnClickListener {

                    onItemClick(img)
                }
            }
        }
    }

}