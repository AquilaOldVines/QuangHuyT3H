package com.example.chitieucanhan.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.DatePickerDialog
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment <V : ViewBinding> : Fragment() {

    private var _binding: V? = null
    protected val binding: V
        get() = requireNotNull(_binding)

    abstract fun bindingLayoutInflater(inflater: LayoutInflater, container: ViewGroup?): V

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = bindingLayoutInflater(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    abstract fun initView()
}