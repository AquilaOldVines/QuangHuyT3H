package com.example.chitieucanhan.fragment.setting

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chitieucanhan.core.BaseFragment
import com.example.chitieucanhan.databinding.FragmentSetingBinding
import com.example.chitieucanhan.fragment.home.ChonDanhMucFragment

class SettingFragment : BaseFragment<FragmentSetingBinding>(){

    override fun bindingLayoutInflater(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSetingBinding {

        return FragmentSetingBinding.inflate(inflater, container, false)
    }

    override fun initView() {

        with(binding){

            txtDanhMuc.setOnClickListener {

                startActivity(Intent(requireActivity(), DanhMucActivity::class.java))
            }

            txtChitieutrongnam.setOnClickListener{

                startActivity(Intent(requireActivity(), ShowOfYear::class.java))
            }
        }
    }
}