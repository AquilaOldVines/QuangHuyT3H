package com.example.chitieucanhan.fragment.setting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chitieucanhan.R
import com.example.chitieucanhan.adapter.settingadapter.HinhDanhMucAdapter
import com.example.chitieucanhan.databinding.ViewAddItemBinding
import com.example.chitieucanhan.model.chi.ChiData
import com.example.chitieucanhan.model.getDataImgChi
import com.example.chitieucanhan.model.getDataImgThu
import com.example.chitieucanhan.model.thu.ThuData
import com.example.chitieucanhan.viewmodel.chiviewmodel.ChiViewModel
import com.example.chitieucanhan.viewmodel.thuviewmodel.ThuViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButtonToggleGroup
import java.util.Arrays
import kotlin.concurrent.thread

class ThemDanhMucFragment : BottomSheetDialogFragment() {

    private lateinit var binding: ViewAddItemBinding

    private val chiViewModel : ChiViewModel by lazy {

        ViewModelProvider(this, ChiViewModel.ChiViewModelFactory(requireActivity().application)).get(ChiViewModel::class.java)
    }

    private val thuViewModel : ThuViewModel by lazy {

        ViewModelProvider(this, ThuViewModel.ThuViewModelFactory(requireActivity().application)).get(ThuViewModel::class.java)
    }

    private var loaiDanhMuc : Int = 1
    private var imgViTien : Int = R.drawable.cat_clipboard
    private var listHinhChi = arrayListOf<Int>()
    private var listHinhThu = arrayListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = ViewAddItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        getDataImgChi().forEach {

            listHinhChi.add(it)
        }

        getDataImgThu().forEach {

            listHinhThu.add(it)
        }

        with(binding){

            tgbLoaiDanhMuc.addOnButtonCheckedListener(MaterialButtonToggleGroup.OnButtonCheckedListener { _, checkedId, _ ->

                when (checkedId) {

                    R.id.btnDanhMucChi -> buttonDanhMucChi()
                    R.id.btnDanhMucThu -> buttonDanhMucThu()
                }
            })

            btnHuyBo.setOnClickListener{

                Toast.makeText(context, "Hủy bỏ", Toast.LENGTH_SHORT).show()
                dismiss()
            }

            btnHoanThanh.setOnClickListener{

                if (txtTenViTien.text.toString() == "") {

                    Toast.makeText(context, "Vui lòng nhập nội dung", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }


                if(loaiDanhMuc == 1){

                    chiViewModel.insertChi(ChiData(txtTenViTien.text.toString(), imgViTien))

                } else if (loaiDanhMuc == 2){

                    thuViewModel.insertThu(ThuData(txtTenViTien.text.toString(), imgViTien))
                }

                Log.d("H370", "$loaiDanhMuc");

                Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show()
                dismiss()
            }

            expanHinhDanhMuc.layoutManager = GridLayoutManager(context, 4)
            adapterImg.submitList(listHinhChi)
            expanHinhDanhMuc.adapter = adapterImg
        }
    }

    private fun buttonDanhMucThu() {

        with(binding){

            btnDanhMucThu.setBackgroundColor(resources.getColor(R.color.button_checked))
            btnDanhMucThu.setTextColor(resources.getColor(R.color.white))
            btnDanhMucChi.setBackgroundColor(resources.getColor(R.color.button_uncheck))
            btnDanhMucChi.setTextColor(resources.getColor(R.color.black))
        }

        loaiDanhMuc = 2
        adapterImg.submitList(listHinhThu)
    }

    private fun buttonDanhMucChi() {

        with(binding){

            btnDanhMucChi.setBackgroundColor(resources.getColor(R.color.button_checked))
            btnDanhMucChi.setTextColor(resources.getColor(R.color.white))
            btnDanhMucThu.setBackgroundColor(resources.getColor(R.color.button_uncheck))
            btnDanhMucThu.setTextColor(resources.getColor(R.color.black))

        }

        loaiDanhMuc = 1
        adapterImg.submitList(listHinhChi)
    }

    private val adapterImg = HinhDanhMucAdapter{

        with(binding){

            imgChonHinh.setImageResource(it)
            imgViTien = it
        }
    }
}