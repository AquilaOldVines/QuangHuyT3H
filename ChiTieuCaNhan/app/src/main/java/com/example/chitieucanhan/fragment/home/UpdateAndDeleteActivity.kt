package com.example.chitieucanhan.fragment.home

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import com.example.chitieucanhan.databinding.ItemViewSetingBinding
import com.example.chitieucanhan.model.user.UserData
import com.example.chitieucanhan.viewmodel.userviewmodel.UserViewModel
import java.util.Calendar
import java.util.Date

class UpdateAndDeleteActivity : AppCompatActivity() {

    private lateinit var binding: ItemViewSetingBinding

    private val userViewModel : UserViewModel by lazy {

        ViewModelProvider(this, UserViewModel.UserViewModelFactory(this.application)).get(
            UserViewModel::class.java)
    }

    private lateinit var date: Date
    val calendar = Calendar.getInstance()
    var mYear = calendar[Calendar.YEAR]
    var mMonth = calendar[Calendar.MONTH]
    var mDay = calendar[Calendar.DAY_OF_MONTH]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ItemViewSetingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        closeUISystem()

        var userData = intent.getSerializableExtra("EXTRA_MESSAGE") as UserData

        with(binding){

            txtNgayChiTieu.setText(userData.dateuser.getDate().toString() + "/" + (userData.dateuser.getMonth() + 1) + "/" + userData.dateuser.getYear());

            if (userData.moneyChiUser != 0f){

                monetUpdate.setText("${userData.moneyChiUser}")

            } else if (userData.moneyThuUser != 0f){

                monetUpdate.setText("${userData.moneyThuUser}")
            }

            imgUpdate.setImageResource(userData.typeUser)
            nameUpdate.text = userData.nameTypeName
            noteUpdate.setText("${userData.noteuser}")
            date = userData.dateuser

            txtNgayChiTieu.setOnClickListener {

                showDatePickerDialog()
            }

            btbGhiDe.setOnClickListener {

                userData.dateuser = date
                userData.noteuser = noteUpdate.text.toString()
                if (userData.moneyChiUser != 0f){

                    userData.moneyChiUser = monetUpdate.text.toString().toFloat()

                } else if (userData.moneyThuUser != 0f){

                    userData.moneyThuUser = monetUpdate.text.toString().toFloat()
                }

                userViewModel.updateUser(userData)
                finish()
            }

            btbXoa.setOnClickListener {

                userViewModel.deleteUser(userData)
                finish()
            }
        }
    }

    private fun showDatePickerDialog() {
        // Lấy ngày hiện tại
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Tạo DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                // Cập nhật TextView sau khi người dùng chọn ngày
                binding.txtNgayChiTieu.text = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                date = Date(selectedYear,selectedMonth,selectedDay)
            },
            year, month, day
        )

        // Hiển thị DatePickerDialog
        datePickerDialog.show()
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