package com.example.chitieucanhan.fragment.thunhap

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chitieucanhan.R
import com.example.chitieucanhan.adapter.thunhapadapter.ShowTiLeAdapter
import com.example.chitieucanhan.core.BaseFragment
import com.example.chitieucanhan.databinding.FragmentReportBinding
import com.example.chitieucanhan.fragment.home.UpdateItemFragment
import com.example.chitieucanhan.model.piechart.DataOfPiechart
import com.example.chitieucanhan.model.user.UserData
import com.example.chitieucanhan.viewmodel.userviewmodel.UserViewModel
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.round

class ThuNhapFragment : BaseFragment<FragmentReportBinding>() {

    private val userViewModel : UserViewModel by lazy {

        ViewModelProvider(this, UserViewModel.UserViewModelFactory(requireActivity().application)).get(UserViewModel::class.java)
    }

    private val calendar = Calendar.getInstance()
    private var mYear = calendar[Calendar.YEAR]
    private var mMonth = calendar[Calendar.MONTH]

    private var tienChiOfMouth : Float = 0f
    private var tienThuOfMouth : Float = 0f
    private var tienTongOfMouth : Float = 0f

    override fun bindingLayoutInflater(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentReportBinding {

        return FragmentReportBinding.inflate(inflater, container, false)
    }

    override fun initView() {

        userViewModel.getAllUser().observe(this, Observer { value ->

            tienChiOfMouth = 0f
            tienThuOfMouth = 0f

            value.forEach { item ->

                if (item.dateuser.month == mMonth){
                    tienChiOfMouth += item.moneyChiUser
                    tienThuOfMouth += item.moneyThuUser
                }
            }

            with(binding){

                txtMouthYears.text = "${mMonth+1}/${mYear}"
                txtTienChi.text = "-${tienChiOfMouth} VNĐ"
                txtTienThu.text = "${tienThuOfMouth} VNĐ"
                txtTienThuNhap.text = "${(tienThuOfMouth - tienChiOfMouth).toString()} VNĐ"

                replaceChildFragment(ChiFragment(mMonth+1))

                tgbDanhMuc.addOnButtonCheckedListener { _, checkedId, _ ->

                    when (checkedId) {

                        R.id.btnDanhMucChi -> buttonDanhMucChi()
                        R.id.btnDanhMucThu -> buttonDanhMucThu()
                    }
                }
            }
        })

        with(binding){

            btbRTien.setOnClickListener {

                if (mMonth > 10){

                    mYear = mYear + 1
                    mMonth = -1
                }

                mMonth = mMonth + 1
                txtMouthYears.text = "${mMonth+1}/${mYear}"
                replaceChildFragment(ChiFragment(mMonth+1))

                userViewModel.getAllUser().observe(this@ThuNhapFragment, Observer { value ->

                    tienChiOfMouth = 0f
                    tienThuOfMouth = 0f

                    value.forEach { item ->

                        if (item.dateuser.month == mMonth){
                            tienChiOfMouth += item.moneyChiUser
                            tienThuOfMouth += item.moneyThuUser
                        }
                    }

                    with(binding){

                        txtMouthYears.text = "${mMonth+1}/${mYear}"
                        txtTienChi.text = "-${tienChiOfMouth} VNĐ"
                        txtTienThu.text = "${tienThuOfMouth} VNĐ"
                        txtTienThuNhap.text = "${(tienThuOfMouth - tienChiOfMouth).toString()} VNĐ"
                    }
                })

                ShowChi()
            }

            btbRlui.setOnClickListener {

                if (mMonth < 1){

                    mYear = mYear - 1
                    mMonth = 12
                }

                mMonth = mMonth - 1
                txtMouthYears.text = "${mMonth+1}/${mYear}"
                replaceChildFragment(ChiFragment(mMonth+1))

                userViewModel.getAllUser().observe(this@ThuNhapFragment, Observer { value ->

                    tienChiOfMouth = 0f
                    tienThuOfMouth = 0f

                    value.forEach { item ->

                        if (item.dateuser.month == mMonth){
                            tienChiOfMouth += item.moneyChiUser
                            tienThuOfMouth += item.moneyThuUser
                        }
                    }

                    with(binding){

                        txtMouthYears.text = "${mMonth+1}/${mYear}"
                        txtTienChi.text = "-${tienChiOfMouth} VNĐ"
                        txtTienThu.text = "${tienThuOfMouth} VNĐ"
                        txtTienThuNhap.text = "${(tienThuOfMouth - tienChiOfMouth).toString()} VNĐ"
                    }
                })

                ShowChi()
            }

            rvItem.layoutManager = LinearLayoutManager(context)
            rvItem.setHasFixedSize(false)
            rvItem.adapter = userAdapter
            ShowChi()
        }
    }

    private val userAdapter = ShowTiLeAdapter{

        ShowChiTietFragment(it.namePie.toString(), mMonth).show(childFragmentManager, ShowChiTietFragment(it.namePie.toString(), mMonth).tag)
    }

    private fun buttonDanhMucThu() {

        with(binding){

            btnDanhMucThu.setBackgroundColor(resources.getColor(R.color.button_checked))
            btnDanhMucThu.setTextColor(resources.getColor(R.color.white))
            btnDanhMucChi.setBackgroundColor(resources.getColor(R.color.button_uncheck))
            btnDanhMucChi.setTextColor(resources.getColor(R.color.black))
        }

        replaceChildFragment(ThuFragment(mMonth+1))
        ShowThu()
    }

    private fun buttonDanhMucChi() {

        with(binding){

            btnDanhMucChi.setBackgroundColor(resources.getColor(R.color.button_checked))
            btnDanhMucChi.setTextColor(resources.getColor(R.color.white))
            btnDanhMucThu.setBackgroundColor(resources.getColor(R.color.button_uncheck))
            btnDanhMucThu.setTextColor(resources.getColor(R.color.black))
        }

        replaceChildFragment(ChiFragment(mMonth+1))
        ShowChi()
    }

    private fun replaceChildFragment(fragment: Fragment) {

        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.flDanhMuc, fragment)
        transaction.addToBackStack(null) // Lưu vào backstack nếu muốn quay lại
        transaction.commit()
    }

    private fun ShowThu(){

        userViewModel.getAllUser().observe(this, Observer { value ->

            var dataOfPiechartThu = arrayListOf<DataOfPiechart>()
            var piechartThu = arrayListOf<DataOfPiechart>()
            val frequencyMapThu = mutableMapOf<String, Int>()
            var tongCong : Float = 0f
            var tongTien : Float = 0f

            value.forEach { item ->

                if (item.dateuser.month == mMonth && item.moneyThuUser != 0f){

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

                dataOfPiechartThu.forEach{ it ->

                    if (it.namePie.toString() == t.toString()){

                        img = it.typePie
                    }
                }

                piechartThu.add(DataOfPiechart(img, t.toString(), tongTien, round((tongTien * 100 / tongCong)).toInt()))
            }

            userAdapter.submitList(piechartThu)
        })
    }

    private fun ShowChi(){

        userViewModel.getAllUser().observe(this, Observer { value ->

            var dataOfPiechartChi = arrayListOf<DataOfPiechart>()
            var piechartChi = arrayListOf<DataOfPiechart>()
            val frequencyMapChi = mutableMapOf<String, Int>()
            var tongCong : Float = 0f
            var tongTien : Float = 0f

            value.forEach { item ->

                if (item.dateuser.month == mMonth && item.moneyChiUser != 0f){

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

                dataOfPiechartChi.forEach{ it ->

                    if (it.namePie.toString() == t.toString()){

                        img = it.typePie
                    }
                }

                piechartChi.add(DataOfPiechart(img, t.toString(), tongTien, round((tongTien * 100 / tongCong)).toInt()))
            }

            userAdapter.submitList(piechartChi)
        })
    }
}