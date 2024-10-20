package com.example.chitieucanhan.adapter.homeadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chitieucanhan.R
import com.example.chitieucanhan.databinding.ItemViewThuchiHomeBinding
import com.example.chitieucanhan.model.user.UserData
import java.util.Date


class ChiTieuAdapter(val onItemClick : (UserData) -> Unit) : ListAdapter<UserData, ChiTieuAdapter.ChiTieuHolder>(

    object : DiffUtil.ItemCallback<UserData>(){

        override fun areItemsTheSame(oldItem: UserData, newItem: UserData): Boolean {

            return oldItem.idUser == newItem.idUser
        }

        override fun areContentsTheSame(oldItem: UserData, newItem: UserData): Boolean {

            return oldItem.moneyTongUser == newItem.moneyTongUser &&
                    oldItem.dateuser == newItem.dateuser &&
                    oldItem.moneyThuUser == newItem.moneyThuUser &&
                    oldItem.moneyChiUser == newItem.moneyChiUser &&
                    oldItem.noteuser == newItem.noteuser &&
                    oldItem.typeUser == newItem.typeUser &&
                    oldItem.nameTypeName == newItem.nameTypeName
        }
    }

) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChiTieuHolder {

        return ChiTieuHolder(

            ItemViewThuchiHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ChiTieuHolder, position: Int) {

        holder.bind(getItem(position))
    }

    inner class ChiTieuHolder(val binding : ItemViewThuchiHomeBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(userData: UserData){

            val date: Date = userData.dateuser

            with(binding){

                imgHinhDanhMuc.setImageResource(userData.typeUser)
                txtTenDanhMuc.text = userData.nameTypeName
                txtGhiChu.text = userData.noteuser
                txtNgayChiTieu.setText(date.getDate().toString() + "/" + (date.getMonth() + 1) + "/" + date.getYear());

                if (userData.moneyChiUser != 0f){

                    txtSoTien.text = userData.moneyChiUser.toString()
                    txtSoTien.setTextColor(ContextCompat.getColor(binding.root.context ,R.color.button_cancel))
                    imgUpDown.setImageResource(R.drawable.ic_down)
                    imgUpDown.setColorFilter(ContextCompat.getColor(binding.root.context, R.color.button_cancel))

                } else if (userData.moneyThuUser != 0f){

                    txtSoTien.text = userData.moneyThuUser.toString()
                    txtSoTien.setTextColor(ContextCompat.getColor(binding.root.context ,R.color.color_icon_checked))
                    imgUpDown.setImageResource(R.drawable.ic_up)
                    imgUpDown.setColorFilter(ContextCompat.getColor(binding.root.context, R.color.color_icon_checked))
                }

                btbSuaXoa.setOnClickListener{

                    onItemClick(userData)
                }
            }
        }
    }

}