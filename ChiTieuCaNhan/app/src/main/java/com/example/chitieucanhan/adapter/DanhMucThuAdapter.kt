package com.example.chitieucanhan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chitieucanhan.databinding.ItemViewTypeBinding
import com.example.chitieucanhan.model.thu.ThuData


class DanhMucThuAdapter(val onItemClick : (ThuData) -> Unit) : ListAdapter<ThuData, DanhMucThuAdapter.DanhMucThuHolder>(

    object : DiffUtil.ItemCallback<ThuData>() {

        override fun areItemsTheSame(oldItem: ThuData, newItem: ThuData): Boolean {

            return oldItem.idThu == newItem.idThu
        }

        override fun areContentsTheSame(oldItem: ThuData, newItem: ThuData): Boolean {

            return oldItem.nameThu == newItem.nameThu && oldItem.imgThu == newItem.imgThu
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DanhMucThuHolder {

        return DanhMucThuHolder(

            ItemViewTypeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: DanhMucThuHolder, position: Int) {

        holder.bind(getItem(position))
    }

    inner class DanhMucThuHolder(val binding: ItemViewTypeBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(thuData: ThuData){

            with(binding){

                imgHinhDanhMuc.setImageResource(thuData.imgThu)
                txtTenViTien.setText(thuData.nameThu)

                imgHinhDanhMuc.setOnClickListener {

                    onItemClick(thuData)
                }
            }
        }
    }
}