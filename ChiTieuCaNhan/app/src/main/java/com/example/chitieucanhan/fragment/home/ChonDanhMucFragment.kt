package com.example.chitieucanhan.fragment.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chitieucanhan.R
import com.example.chitieucanhan.adapter.DanhMucChiAdapter
import com.example.chitieucanhan.adapter.DanhMucThuAdapter
import com.example.chitieucanhan.databinding.SetupViewTypeBinding
import com.example.chitieucanhan.model.chi.ChiData
import com.example.chitieucanhan.model.thu.ThuData
import com.example.chitieucanhan.viewmodel.chiviewmodel.ChiViewModel
import com.example.chitieucanhan.viewmodel.thuviewmodel.ThuViewModel
import com.example.chitieucanhan.viewmodel.userviewmodel.UserViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButtonToggleGroup.OnButtonCheckedListener

class ChonDanhMucFragment(private var listener: BottomSheetListener) : BottomSheetDialogFragment() {

    private lateinit var binding : SetupViewTypeBinding

    private val thuViewModel : ThuViewModel by lazy {

        ViewModelProvider(this, ThuViewModel.ThuViewModelFactory(requireActivity().application)).get(ThuViewModel::class.java)
    }

    private val chiViewModel : ChiViewModel by lazy {

        ViewModelProvider(this, ChiViewModel.ChiViewModelFactory (requireActivity().application)).get(ChiViewModel::class.java)
    }

    interface BottomSheetListener{

        fun onButtonClickedThu(thuData: ThuData, idThu : Int)
        fun onButtonClickedChi(chiData: ChiData, idChi : Int)
    }

    private var checkDanhMuc = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = SetupViewTypeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            rvChonDanhMuc.layoutManager = GridLayoutManager(context, 4)
            rvChonDanhMuc.adapter = adapterChi
            getListDanhMucChi(chiViewModel.getAllChi())

            binding.tgbLoaiDanhMuc.addOnButtonCheckedListener(OnButtonCheckedListener { _, checkedId, _ ->

                when (checkedId) {

                    R.id.btnDanhMucChi -> buttonDanhMucChi()
                    R.id.btnDanhMucThu -> buttonDanhMucThu()
                }
            })
        }
    }

    private fun buttonDanhMucThu(){

        with(binding){

            btnDanhMucThu.setBackgroundColor(resources.getColor(R.color.button_checked))
            btnDanhMucThu.setTextColor(resources.getColor(R.color.white))
            btnDanhMucChi.setBackgroundColor(resources.getColor(R.color.button_uncheck))
            btnDanhMucChi.setTextColor(resources.getColor(R.color.black))

            rvChonDanhMuc.adapter = adapterThu
        }

        getListDanhMucThu(thuViewModel.getAllThu())
        checkDanhMuc = 2;
    }

    private fun buttonDanhMucChi(){

        with(binding){

            btnDanhMucChi.setBackgroundColor(resources.getColor(R.color.button_checked))
            btnDanhMucChi.setTextColor(resources.getColor(R.color.white))
            btnDanhMucThu.setBackgroundColor(resources.getColor(R.color.button_uncheck))
            btnDanhMucThu.setTextColor(resources.getColor(R.color.black))

            rvChonDanhMuc.adapter = adapterChi

        }

        getListDanhMucChi(chiViewModel.getAllChi())
        checkDanhMuc = 1;
    }

    private fun getListDanhMucThu(listDanhMucThu: LiveData<List<ThuData>>) {

        listDanhMucThu.observe(activity!!, object : Observer<List<ThuData?>?> {

            override fun onChanged(value: List<ThuData?>?) {

                adapterThu.submitList(value)
            }
        })
    }

    private val adapterThu = DanhMucThuAdapter{

        listener.onButtonClickedThu(it,checkDanhMuc)
        dismiss()
    }

    private fun getListDanhMucChi(listDanhMucThu: LiveData<List<ChiData>>) {

        listDanhMucThu.observe(activity!!, object : Observer<List<ChiData?>?> {

            override fun onChanged(value: List<ChiData?>?) {

                adapterChi.submitList(value)
            }
        })
    }

    private val adapterChi = DanhMucChiAdapter{

        listener.onButtonClickedChi(it,checkDanhMuc)
        dismiss()
    }
}