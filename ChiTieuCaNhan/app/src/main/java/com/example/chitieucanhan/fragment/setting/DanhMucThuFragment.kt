package com.example.chitieucanhan.fragment.setting

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chitieucanhan.R
import com.example.chitieucanhan.adapter.DanhMucThuAdapter
import com.example.chitieucanhan.core.BaseFragment
import com.example.chitieucanhan.databinding.FragmentDanhmucthuBinding
import com.example.chitieucanhan.model.chi.ChiData
import com.example.chitieucanhan.model.thu.ThuData
import com.example.chitieucanhan.viewmodel.thuviewmodel.ThuViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlin.concurrent.thread

class DanhMucThuFragment : BaseFragment<FragmentDanhmucthuBinding>() {

    private val thuViewModel : ThuViewModel by lazy {

        ViewModelProvider(this, ThuViewModel.ThuViewModelFactory(requireActivity().application)).get(ThuViewModel::class.java)
    }

    override fun bindingLayoutInflater(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDanhmucthuBinding {

        return FragmentDanhmucthuBinding.inflate(inflater, container, false)
    }

    override fun initView() {

        with(binding){


            rvDanhMucThu.layoutManager = GridLayoutManager(context, 4)
            rvDanhMucThu.adapter = adapterDMThu
            showListDanhMucThu(thuViewModel.getAllThu())

        }
    }

    private fun showListDanhMucThu(listDanhMucThu: LiveData<List<ThuData>>) {

        listDanhMucThu.observe(requireActivity()) { value ->

            adapterDMThu.submitList(value)
        }
    }

    private val adapterDMThu = DanhMucThuAdapter{

        showBottomSheetDanhMucChi(it)
    }

    private fun showBottomSheetDanhMucChi(thuData: ThuData) {

        val bottomSheetDialog = BottomSheetDialog(context!!)
        bottomSheetDialog.setContentView(R.layout.seting_item)
        bottomSheetDialog.show()

        val btnXoa = bottomSheetDialog.findViewById<Button>(R.id.btnXoa)
        val btnChinhSua = bottomSheetDialog.findViewById<Button>(R.id.btnChinhSua)

        btnXoa?.setOnClickListener {

            thuViewModel.deleteThu(thuData)
            bottomSheetDialog.dismiss()
        }
    }
}