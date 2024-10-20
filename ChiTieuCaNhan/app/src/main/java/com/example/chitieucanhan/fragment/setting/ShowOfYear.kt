package com.example.chitieucanhan.fragment.setting

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chitieucanhan.R
import com.example.chitieucanhan.activity.ContainerActivity
import com.example.chitieucanhan.adapter.thunhapadapter.ShowTiLeAdapter
import com.example.chitieucanhan.databinding.ActivityMainBinding
import com.example.chitieucanhan.databinding.FragmentShowOfYearBinding
import com.example.chitieucanhan.databinding.ViewAddItemBinding
import com.example.chitieucanhan.fragment.thunhap.ChiFragment
import com.example.chitieucanhan.fragment.thunhap.ShowChiTietFragment
import com.example.chitieucanhan.model.piechart.DataOfPiechart
import com.example.chitieucanhan.viewmodel.userviewmodel.UserViewModel
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar
import kotlin.math.round

class ShowOfYear : AppCompatActivity() {

    private lateinit var binding: FragmentShowOfYearBinding

    private val calendar = Calendar.getInstance()
    private var mYear = calendar[Calendar.YEAR]

    private val userViewModel : UserViewModel by lazy {

        ViewModelProvider(this, UserViewModel.UserViewModelFactory(this.application)).get(UserViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = FragmentShowOfYearBinding.inflate(layoutInflater)
        setContentView(binding.root)
        closeUISystem()

        with(binding){

            tgbDanhMuc.addOnButtonCheckedListener { _, checkedId, _ ->

                when (checkedId) {

                    R.id.btnDanhMucChi -> buttonDanhMucChi()
                    R.id.btnDanhMucThu -> buttonDanhMucThu()
                }
            }

            rvOfYears.layoutManager = LinearLayoutManager(this@ShowOfYear)
            rvOfYears.setHasFixedSize(false)
            rvOfYears.adapter = userAdapter

            ShowChi()
        }
    }


    private val userAdapter = ShowTiLeAdapter{


    }

    private fun buttonDanhMucChi() {

        with(binding){

            btnDanhMucChi.setBackgroundColor(resources.getColor(R.color.button_checked))
            btnDanhMucChi.setTextColor(resources.getColor(R.color.white))
            btnDanhMucThu.setBackgroundColor(resources.getColor(R.color.button_uncheck))
            btnDanhMucThu.setTextColor(resources.getColor(R.color.black))
        }

        ShowChi()
    }

    private fun buttonDanhMucThu() {

        with(binding){

            btnDanhMucThu.setBackgroundColor(resources.getColor(R.color.button_checked))
            btnDanhMucThu.setTextColor(resources.getColor(R.color.white))
            btnDanhMucChi.setBackgroundColor(resources.getColor(R.color.button_uncheck))
            btnDanhMucChi.setTextColor(resources.getColor(R.color.black))
        }

        ShowThu()
    }

    private fun ShowThu(){

        userViewModel.getAllUser().observe(this, Observer { value ->

            var dataOfPiechartThu = arrayListOf<DataOfPiechart>()
            var piechartThu = arrayListOf<DataOfPiechart>()
            val frequencyMapThu = mutableMapOf<String, Int>()
            var tongCong : Float = 0f
            var tongTien : Float = 0f

            var entries = mutableListOf<PieEntry>()
            var dataSet = PieDataSet(entries, "Tiền chi")
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

                if (item.moneyThuUser != 0f){

                    var dataPie = DataOfPiechart(item.typeUser, item.nameTypeName, item.moneyThuUser, 0)
                    dataOfPiechartThu.add(dataPie)
                }
            }

            dataOfPiechartThu.forEach { item ->

                tongCong = tongCong + item.moneyPie
                frequencyMapThu[item.namePie] = frequencyMapThu.getOrDefault(item.namePie, 0) + 1
            }

            frequencyMapThu.forEach { t, _ ->

                tongTien = 0f
                var img : Int = 0
                dataOfPiechartThu.forEach{ it ->

                    if (it.namePie.toString() == t.toString()){

                        tongTien = tongTien + it.moneyPie
                    }
                }
                var tile = round(tongTien * 100 / tongCong)
                entries.add(PieEntry(tile.toFloat(), t.toString()))

                dataOfPiechartThu.forEach{ it ->

                    if (it.namePie.toString() == t.toString()){

                        img = it.typePie
                    }
                }
                piechartThu.add(DataOfPiechart(img, t.toString(), tongTien, round((tongTien * 100 / tongCong)).toInt()))
            }

            piechartThu.forEach {

                Log.d("H109", "${it}");
            }

            userAdapter.submitList(piechartThu)

            with(binding){

                pieOfYears.data = pieData
                pieOfYears.invalidate() // Refresh biểu đồ
            }
        })
    }

    private fun ShowChi(){

        userViewModel.getAllUser().observe(this, Observer { value ->

            var dataOfPiechartChi = arrayListOf<DataOfPiechart>()
            var piechartChi = arrayListOf<DataOfPiechart>()
            val frequencyMapChi = mutableMapOf<String, Int>()
            var tongCong : Float = 0f
            var tongTien : Float = 0f

            var entries = mutableListOf<PieEntry>()
            var dataSet = PieDataSet(entries, "Tiền chi")
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

                if (item.moneyChiUser != 0f){

                    var dataPie = DataOfPiechart(item.typeUser, item.nameTypeName, item.moneyChiUser, 0)
                    dataOfPiechartChi.add(dataPie)
                }
            }

            dataOfPiechartChi.forEach { item ->

                tongCong = tongCong + item.moneyPie
                frequencyMapChi[item.namePie] = frequencyMapChi.getOrDefault(item.namePie, 0) + 1
            }

            frequencyMapChi.forEach { t, _ ->

                tongTien = 0f
                var img : Int = 0
                dataOfPiechartChi.forEach{ it ->

                    if (it.namePie.toString() == t.toString()){

                        tongTien = tongTien + it.moneyPie
                    }
                }

                var tile = round(tongTien * 100 / tongCong)
                entries.add(PieEntry(tile.toFloat(), t.toString()))

                dataOfPiechartChi.forEach{ it ->

                    if (it.namePie.toString() == t.toString()){

                        img = it.typePie
                    }

                }
                piechartChi.add(DataOfPiechart(img, t.toString(), tongTien, round((tongTien * 100 / tongCong)).toInt()))


            }

            userAdapter.submitList(piechartChi)

            with(binding){

                pieOfYears.data = pieData
                pieOfYears.invalidate() // Refresh biểu đồ
            }
        })
    }

    private fun closeUISystem(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            window.insetsController?.let {
                it.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            // Dành cho các phiên bản Android cũ hơn (API 29 trở xuống)
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }
}