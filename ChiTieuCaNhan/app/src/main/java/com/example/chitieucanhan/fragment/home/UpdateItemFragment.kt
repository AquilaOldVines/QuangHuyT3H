package com.example.chitieucanhan.fragment.home

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.chitieucanhan.databinding.ItemViewSetingBinding
import com.example.chitieucanhan.fragment.home.ChonDanhMucFragment.BottomSheetListener
import com.example.chitieucanhan.model.chi.ChiData
import com.example.chitieucanhan.model.thu.ThuData
import com.example.chitieucanhan.model.user.UserData
import com.example.chitieucanhan.viewmodel.userviewmodel.UserViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar
import java.util.Date

// đang trong quá trình sửa để tối ưu
class UpdateItemFragment(private var userData: UserData, private var listener: BottomSheetListener) : BottomSheetDialogFragment() {

    private lateinit var binding : ItemViewSetingBinding

    interface BottomSheetListener{

        fun onButtonClickedUpdate(userData: UserData)
    }

    private val userViewModel : UserViewModel by lazy {

        ViewModelProvider(this, UserViewModel.UserViewModelFactory(requireActivity().application)).get(UserViewModel::class.java)
    }

    private lateinit var date: Date
    val calendar = Calendar.getInstance()
    var mYear = calendar[Calendar.YEAR]
    var mMonth = calendar[Calendar.MONTH]
    var mDay = calendar[Calendar.DAY_OF_MONTH]

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = ItemViewSetingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

            txtNgayChiTieu.setOnClickListener {

                showDatePickerDialog()
            }

            imgUpdate.setOnClickListener {


            }

            btbGhiDe.setOnClickListener {

//                userData.dateuser = date
                userData.noteuser = noteUpdate.text.toString()
                if (userData.moneyChiUser != 0f){

                    userData.moneyChiUser = monetUpdate.text.toString().toFloat()

                } else if (userData.moneyThuUser != 0f){

                    userData.moneyThuUser = monetUpdate.text.toString().toFloat()
                }

                listener.onButtonClickedUpdate(userData)
                dismiss()
            }

            btbXoa.setOnClickListener {

                userViewModel.deleteUser(userData)
                dismiss()
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
            requireContext(),
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
}