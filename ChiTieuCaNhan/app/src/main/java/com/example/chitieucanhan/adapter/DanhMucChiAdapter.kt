package com.example.chitieucanhan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chitieucanhan.databinding.ItemViewTypeBinding
import com.example.chitieucanhan.model.chi.ChiData

class DanhMucChiAdapter(val onItemClick : (ChiData) -> Unit) : ListAdapter<ChiData, DanhMucChiAdapter.DanhMucChiHolder>(

    object : DiffUtil.ItemCallback<ChiData>(){

        override fun areItemsTheSame(oldItem: ChiData, newItem: ChiData): Boolean {

            return oldItem.idChi == oldItem.idChi
        }

        override fun areContentsTheSame(oldItem: ChiData, newItem: ChiData): Boolean {

            return oldItem.nameChi == newItem.nameChi && oldItem.imgChi == newItem.imgChi
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DanhMucChiHolder {

        return DanhMucChiHolder(

            ItemViewTypeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: DanhMucChiHolder, position: Int) {

        holder.bind(getItem(position))
    }

    inner class DanhMucChiHolder(val binding: ItemViewTypeBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(chiData: ChiData){

            with(binding){

                imgHinhDanhMuc.setImageResource(chiData.imgChi)
                txtTenViTien.setText(chiData.nameChi)

                imgHinhDanhMuc.setOnClickListener {

                    onItemClick(chiData)
                }
            }
        }
    }
}