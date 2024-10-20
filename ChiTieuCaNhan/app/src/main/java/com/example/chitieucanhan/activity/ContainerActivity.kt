package com.example.chitieucanhan.activity

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chitieucanhan.R
import com.example.chitieucanhan.databinding.ActivityContainerBinding
import com.example.chitieucanhan.fragment.home.HomeFragment
import com.example.chitieucanhan.fragment.setting.SettingFragment
import com.example.chitieucanhan.fragment.thunhap.ThuNhapFragment
import com.example.chitieucanhan.model.chi.ChiData
import com.example.chitieucanhan.model.thu.ThuData
import com.example.chitieucanhan.model.thu.ThuDatabase
import com.example.chitieucanhan.viewmodel.chiviewmodel.ChiViewModel
import com.example.chitieucanhan.viewmodel.thuviewmodel.ThuViewModel
import com.example.chitieucanhan.viewmodel.userviewmodel.UserViewModel
import kotlin.concurrent.thread

class ContainerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContainerBinding

    private val chiViewModel : ChiViewModel by lazy {

        ViewModelProvider(this, ChiViewModel.ChiViewModelFactory(this.application)).get(ChiViewModel::class.java)
    }

    private val thuViewModel : ThuViewModel by lazy {

        ViewModelProvider(this, ThuViewModel.ThuViewModelFactory (this.application)).get(ThuViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtTBTitle.text = "Trang chủ"
        loadFragment(HomeFragment())

        with(binding){

            bnvContainer.setOnItemSelectedListener {

                when(it.itemId){

                    R.id.menuHome -> {

                        txtTBTitle.text = "Trang chủ"
                        loadFragment(HomeFragment())
                    }

                    R.id.menuThuNhap -> {

                        txtTBTitle.text = "Thu chi"
                        loadFragment(ThuNhapFragment())
                    }

                    R.id.menuCaiDat -> {

                        txtTBTitle.text = "Cài đặt"
                        loadFragment(SettingFragment())
                    }
                }

                true
            }
        }

        closeUISystem()
    }

    private fun loadFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction()
            .replace(binding.vpContaner.id, fragment)
            .commit()
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