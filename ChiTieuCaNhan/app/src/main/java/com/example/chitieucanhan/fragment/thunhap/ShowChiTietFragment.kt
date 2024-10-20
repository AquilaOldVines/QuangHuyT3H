package com.example.chitieucanhan.fragment.thunhap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.chitieucanhan.adapter.homeadapter.ChiTieuAdapter
import com.example.chitieucanhan.databinding.ShowChiTietFragmentBinding
import com.example.chitieucanhan.fragment.home.UpdateItemFragment
import com.example.chitieucanhan.model.user.UserData
import com.example.chitieucanhan.viewmodel.userviewmodel.UserViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ShowChiTietFragment(private var nameItem: String, private var month : Int) : BottomSheetDialogFragment() {

    private lateinit var binding: ShowChiTietFragmentBinding

    private val userViewModel : UserViewModel by lazy {

        ViewModelProvider(this, UserViewModel.UserViewModelFactory(requireActivity().application)).get(UserViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ShowChiTietFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel.getAllNameUser(nameItem).observe(this, Observer { value ->

            var arr = arrayListOf<UserData>()

            value.forEach { item ->

                if(item.dateuser.month == month){

                    arr.add(item)
                }
            }

            adapterUser.submitList(arr)

            with(binding){

                showChiTiet.layoutManager = LinearLayoutManager(context)
                showChiTiet.adapter = adapterUser
            }
        })
    }

    private var adapterUser = ChiTieuAdapter{

        Toast.makeText(context, "Đang Update", Toast.LENGTH_SHORT).show()
    }
}