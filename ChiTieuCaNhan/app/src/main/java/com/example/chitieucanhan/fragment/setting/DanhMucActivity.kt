package com.example.chitieucanhan.fragment.setting

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.chitieucanhan.R
import com.example.chitieucanhan.databinding.ViewDanhmucBinding
import com.example.chitieucanhan.viewmodel.chiviewmodel.ChiViewModel
import com.example.chitieucanhan.viewmodel.thuviewmodel.ThuViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog

class DanhMucActivity : AppCompatActivity() {

    private lateinit var binding: ViewDanhmucBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ViewDanhmucBinding.inflate(layoutInflater)
        setContentView(binding.root)
        closeUISystem()

        fragmentTransaction(DanhMucChiFragment())

        with(binding){

            tgbDanhMuc.addOnButtonCheckedListener { _, checkedId, _ ->

                when (checkedId) {

                    R.id.btnDanhMucChi -> buttonDanhMucChi()
                    R.id.btnDanhMucThu -> buttonDanhMucThu()

                }
            }

            tbDanhMuc.setOnMenuItemClickListener {

                when(it.itemId){

                    R.id.menuThem -> {

                        showBottomSheetThemDanhMuc()
                        true
                    }

                    else -> {

                        false
                    }
                }
            }

            tbDanhMuc.setOnClickListener{

                onBackPressed()
            }

        }

    }

    private fun showBottomSheetThemDanhMuc(){

        ThemDanhMucFragment().show(supportFragmentManager, ThemDanhMucFragment().tag)
    }


    private fun buttonDanhMucThu() {

        with(binding){

            btnDanhMucThu.setBackgroundColor(resources.getColor(R.color.button_checked))
            btnDanhMucThu.setTextColor(resources.getColor(R.color.white))
            btnDanhMucChi.setBackgroundColor(resources.getColor(R.color.button_uncheck))
            btnDanhMucChi.setTextColor(resources.getColor(R.color.black))

        }
        fragmentTransaction(DanhMucThuFragment())
    }

    private fun buttonDanhMucChi() {

        with(binding){

            btnDanhMucChi.setBackgroundColor(resources.getColor(R.color.button_checked))
            btnDanhMucChi.setTextColor(resources.getColor(R.color.white))
            btnDanhMucThu.setBackgroundColor(resources.getColor(R.color.button_uncheck))
            btnDanhMucThu.setTextColor(resources.getColor(R.color.black))

        }
        fragmentTransaction(DanhMucChiFragment())
    }

    private fun fragmentTransaction(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        if (supportFragmentManager.findFragmentById(R.id.flDanhMuc) != null) {

            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        fragmentTransaction.replace(R.id.flDanhMuc, fragment, null)
        fragmentTransaction.commit()
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
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    )
        }
    }
}