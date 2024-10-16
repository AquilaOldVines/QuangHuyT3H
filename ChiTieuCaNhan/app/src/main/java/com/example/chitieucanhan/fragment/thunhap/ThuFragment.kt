package com.example.chitieucanhan.fragment.thunhap

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chitieucanhan.core.BaseFragment
import com.example.chitieucanhan.databinding.FragmentShowChitietBinding
import com.example.chitieucanhan.model.piechart.DataOfPiechart
import com.example.chitieucanhan.viewmodel.userviewmodel.UserViewModel
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlin.math.round

class ThuFragment(private val checkMouth : Int) : BaseFragment<FragmentShowChitietBinding>() {

    override fun bindingLayoutInflater(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentShowChitietBinding {

        return FragmentShowChitietBinding.inflate(inflater, container, false)
    }

    private val userViewModelThu : UserViewModel by lazy {

        ViewModelProvider(this, UserViewModel.UserViewModelFactory(requireActivity().application)).get(UserViewModel::class.java)
    }

    override fun initView() {

        userViewModelThu.getAllUser().observe(this, Observer { value ->

            val frequencyMap = mutableMapOf<String, Int>()
            var dataOfPiechart = arrayListOf<DataOfPiechart>()
            var tongTien : Double = 0.0
            var tongCong : Double = 0.0
            var entries = mutableListOf<PieEntry>()
            var dataSet = PieDataSet(entries, "Tiền thu ${checkMouth}")
            var pieData = PieData(dataSet)

            dataSet.setColors(intArrayOf(
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light,
                android.R.color.holo_green_dark,
                android.R.color.holo_red_dark,
                android.R.color.system_accent1_800,
                android.R.color.system_accent1_700,
                android.R.color.system_accent1_600
            ), binding.root.context)

            value.forEach { item ->

                if (item.dateuser.month == checkMouth-1 && item.moneyThuUser != 0f){

                    var dataPie : DataOfPiechart = DataOfPiechart(item.typeUser,item.nameTypeName, item.moneyThuUser, 0)
                    dataOfPiechart.add(dataPie)
                }
            }

            dataOfPiechart.forEach { item ->

                tongCong = tongCong + item.moneyPie
                frequencyMap[item.namePie] = frequencyMap.getOrDefault(item.namePie, 0) + 1
            }

            frequencyMap.forEach { t, _ ->

                tongTien = 0.0
                dataOfPiechart.forEach{ it ->

                    if (it.namePie.toString() == t.toString()){

                        tongTien = tongTien + it.moneyPie
                    }
                }
                var tile = round(tongTien * 100 / tongCong)
                entries.add(PieEntry(tile.toFloat(), t.toString()))
            }

            entries.forEach {

                Log.d("H109", "${it}");
            }

            with(binding){

                showPiechart.data = pieData
                showPiechart.invalidate() // Refresh biểu đồ
            }
        })
    }
}