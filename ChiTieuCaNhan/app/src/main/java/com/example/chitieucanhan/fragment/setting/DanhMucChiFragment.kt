package com.example.chitieucanhan.fragment.setting

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chitieucanhan.R
import com.example.chitieucanhan.adapter.DanhMucChiAdapter
import com.example.chitieucanhan.core.BaseFragment
import com.example.chitieucanhan.databinding.FragmentDanhmucchiBinding
import com.example.chitieucanhan.model.chi.ChiData
import com.example.chitieucanhan.viewmodel.chiviewmodel.ChiViewModel
import com.example.chitieucanhan.viewmodel.thuviewmodel.ThuViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlin.concurrent.thread

class DanhMucChiFragment : BaseFragment<FragmentDanhmucchiBinding>() {

    private val chiViewModel : ChiViewModel by lazy {

        ViewModelProvider(this, ChiViewModel.ChiViewModelFactory(requireActivity().application)).get(ChiViewModel::class.java)
    }

    override fun bindingLayoutInflater(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDanhmucchiBinding {

        return FragmentDanhmucchiBinding.inflate(inflater, container, false)
    }

    override fun initView() {

        with(binding){

            rvDanhMucChi.layoutManager = GridLayoutManager(context, 4)
            rvDanhMucChi.adapter = adapterDMChi
            showListDanhMucChi(chiViewModel.getAllChi())
        }

    }

    private fun showListDanhMucChi(listDanhMucThu: LiveData<List<ChiData>>) {

        listDanhMucThu.observe(requireActivity()) { value ->

            adapterDMChi.submitList(value)
        }
    }

    private val adapterDMChi = DanhMucChiAdapter{

        showBottomSheetDanhMucChi(it)
    }

    private fun showBottomSheetDanhMucChi(chiData: ChiData) {

        val bottomSheetDialog = BottomSheetDialog(context!!)
        bottomSheetDialog.setContentView(R.layout.seting_item)
        bottomSheetDialog.show()

        val btnXoa = bottomSheetDialog.findViewById<Button>(R.id.btnXoa)
        val btnChinhSua = bottomSheetDialog.findViewById<Button>(R.id.btnChinhSua)

        btnXoa?.setOnClickListener {

            chiViewModel.deleteChi(chiData)
            bottomSheetDialog.dismiss()
        }
    }
}