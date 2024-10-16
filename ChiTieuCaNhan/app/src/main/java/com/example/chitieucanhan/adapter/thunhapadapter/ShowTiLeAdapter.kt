package com.example.chitieucanhan.adapter.thunhapadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chitieucanhan.databinding.ItemViewThuchiHomeBinding
import com.example.chitieucanhan.databinding.ItemViewThuchiReportBinding
import com.example.chitieucanhan.model.piechart.DataOfPiechart

class ShowTiLeAdapter(val onItemClick : (DataOfPiechart) -> Unit) : ListAdapter<DataOfPiechart, ShowTiLeAdapter.ShowTiLeHolder>(

    object : DiffUtil.ItemCallback<DataOfPiechart>(){

        override fun areItemsTheSame(oldItem: DataOfPiechart, newItem: DataOfPiechart): Boolean {

            return oldItem.namePie == newItem.namePie
        }

        override fun areContentsTheSame(oldItem: DataOfPiechart, newItem: DataOfPiechart): Boolean {

            return oldItem.typePie == newItem.typePie &&
                    oldItem.moneyPie == newItem.moneyPie &&
                    oldItem.ratioPie == newItem.ratioPie
        }
    }

) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowTiLeHolder {

        return ShowTiLeHolder(

            ItemViewThuchiReportBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ShowTiLeHolder, position: Int) {

        holder.bind(getItem(position))
    }

    inner class ShowTiLeHolder(val binding : ItemViewThuchiReportBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(dataOfPiechart: DataOfPiechart){

            with(binding){

                imgPie.setImageResource(dataOfPiechart.typePie)
                namePie.text = dataOfPiechart.namePie
                moneyPie.text = "${dataOfPiechart.moneyPie} VNĐ"
                tilePie.text = "${dataOfPiechart.ratioPie} %"

                bamItem.setOnClickListener {

                    onItemClick(dataOfPiechart)
                }
            }
        }
    }
}