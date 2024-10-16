package com.example.chitieucanhan.fragment.home

import android.app.DatePickerDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chitieucanhan.R
import com.example.chitieucanhan.adapter.homeadapter.ChiTieuAdapter
import com.example.chitieucanhan.core.BaseFragment
import com.example.chitieucanhan.databinding.FragmentHomeBinding
import com.example.chitieucanhan.model.chi.ChiData
import com.example.chitieucanhan.model.thu.ThuData
import com.example.chitieucanhan.model.user.UserData
import com.example.chitieucanhan.viewmodel.userviewmodel.UserViewModel
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.YearMonth
import java.util.Calendar
import java.util.Date
import java.util.Locale

class HomeFragment : BaseFragment<FragmentHomeBinding>(), ChonDanhMucFragment.BottomSheetListener {

    private val userViewModel : UserViewModel by lazy {

        ViewModelProvider(this, UserViewModel.UserViewModelFactory(requireActivity().application)).get(UserViewModel::class.java)
    }

    private lateinit var date: Date
    var calendar = Calendar.getInstance()
    var mYear = calendar[Calendar.YEAR]
    var mMonth = calendar[Calendar.MONTH]
    var mDay = calendar[Calendar.DAY_OF_MONTH]

    private var arr = arrayListOf<UserData>()
    private var imageViewItem : Int = R.drawable.cat_clipboard
    private var checkThuChi : Int = 1
    private var earlyWeek : Int = 0
    private var ngayTh2 : String = ""
    private var mDayTh2 : Int = 0

    private var th2 : Int = 0
    private var th3 : Int = 0
    private var th4 : Int = 0
    private var th5 : Int = 0
    private var th6 : Int = 0
    private var th7 : Int = 0
    private var tCn : Int = 0

    override fun bindingLayoutInflater(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {

        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initView() {

        date = Date(mYear, mMonth, mDay)
        checkNgay(mYear.toString() + "-" + (mMonth + 1) + "-" + mDay)
        showDayofWeek(ngayTh2)

        userViewModel.getAllDateUser(date).observe(this@HomeFragment, Observer { value ->

            adapterUser.submitList(value)
        })

        with(binding){

            txtMouth.setText((mMonth + 1).toString() + "/" + mYear)
            txtNgayChiTieu.setText(mDay.toString() + "/" + (mMonth + 1) + "/" + mYear)

            txtNgayChiTieu.setOnClickListener {

                showDatePickerDialog()
            }

            btbTien.setOnClickListener {

                if (mDayTh2 + 7 < getDaysInMonth(mYear, mMonth+1)){

                    mDayTh2 = mDayTh2 + 7

                } else if (mDayTh2 + 7 > getDaysInMonth(mYear, mMonth+1))  {

                    mMonth = mMonth + 1
                    mDayTh2 = 7 - (getDaysInMonth(mYear, mMonth) - mDayTh2 + 1) + 1
                    txtMouth.setText((mMonth + 1).toString() + "/" + mYear)

                } else if (mDayTh2 + 7 == getDaysInMonth(mYear, mMonth+1)){

                    mDayTh2 = getDaysInMonth(mYear, mMonth+1)
                }

                showDayofWeek(ngayTh2)
            }

            btbLui.setOnClickListener {

                if (mDayTh2 - 7 >= 1){

                    mDayTh2 = mDayTh2 - 7

                } else if (mDayTh2 - 7 <= 1) {

                    mMonth = mMonth -1
                    mDayTh2 = getDaysInMonth(mYear, mMonth+1) - (7 - mDayTh2)
                    txtMouth.setText((mMonth + 1).toString() + "/" + mYear)

                } else if (mDayTh2 - 7 == 0){

                    mDayTh2 = getDaysInMonth(mYear, mMonth)
                }

                showDayofWeek(ngayTh2)
            }

            imgItem.setOnClickListener {

                ChonDanhMucFragment(this@HomeFragment).show(childFragmentManager, ChonDanhMucFragment(this@HomeFragment).tag)
            }

            txtTh2.setOnClickListener {

                date = Date(mYear, mMonth, th2)
                userViewModel.getAllDateUser(date).observe(this@HomeFragment, Observer { value ->

                    adapterUser.submitList(value)
                })
            }

            txtTh3.setOnClickListener {

                date = Date(mYear, mMonth, th3)
                userViewModel.getAllDateUser(date).observe(this@HomeFragment, Observer { value ->

                    adapterUser.submitList(value)
                })
            }

            txtTh4.setOnClickListener {

                date = Date(mYear, mMonth, th4)
                userViewModel.getAllDateUser(date).observe(this@HomeFragment, Observer { value ->

                    adapterUser.submitList(value)
                })
            }

            txtTh5.setOnClickListener {

                date = Date(mYear, mMonth, th5)
                userViewModel.getAllDateUser(date).observe(this@HomeFragment, Observer { value ->

                    adapterUser.submitList(value)
                })
            }

            txtTh6.setOnClickListener {

                date = Date(mYear, mMonth, th6)
                userViewModel.getAllDateUser(date).observe(this@HomeFragment, Observer { value ->

                    adapterUser.submitList(value)
                })
            }

            txtTh7.setOnClickListener {

                date = Date(mYear, mMonth, th7)
                userViewModel.getAllDateUser(date).observe(this@HomeFragment, Observer { value ->

                    adapterUser.submitList(value)
                })
            }

            txtCn.setOnClickListener {

                date = Date(mYear, mMonth, tCn)
                userViewModel.getAllDateUser(date).observe(this@HomeFragment, Observer { value ->

                    adapterUser.submitList(value)
                })
            }

            btbNhap.setOnClickListener {

                if(moneyViewHome.text == null){

                    return@setOnClickListener
                }

                if(checkThuChi == 1){

                    var userData = UserData(
                        0f,
                        date,
                        0f,
                        moneyViewHome.text.toString().toFloat(),
                        noteUser.text.toString(),
                        imageViewItem,
                        nameItem.text.toString()
                    )

                    userViewModel.insertUser(userData)

                } else if (checkThuChi == 2) {

                    var userData = UserData(
                        0f,
                        date,
                        moneyViewHome.text.toString().toFloat(),
                        0f,
                        noteUser.text.toString(),
                        imageViewItem,
                        nameItem.text.toString()
                    )

                    userViewModel.insertUser(userData)
                }

                txtNgayChiTieu.setText(mDay.toString() + "/" + (mMonth + 1) + "/" + mYear)
                moneyViewHome.setText("")
                noteUser.setText("")
            }

            rvThuChiUser.layoutManager = LinearLayoutManager(context)
            rvThuChiUser.adapter = adapterUser

            userViewModel.getAllDateUser(date).observe(this@HomeFragment, Observer { value ->

                adapterUser.submitList(value)
            })
        }

    }

    private var adapterUser = ChiTieuAdapter{

        val intent = Intent(binding.root.context, UpdateAndDeleteActivity::class.java)
        intent.putExtra("EXTRA_MESSAGE",it)
        startActivity(intent)
        // UpdateItemFragment(it,this@HomeFragment).show(childFragmentManager, UpdateItemFragment(it,this@HomeFragment).tag)
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

    override fun onButtonClickedThu(thuData: ThuData, idThu: Int) {

        checkThuChi = idThu
        with(binding){

            typeDanhMuc.text = "Thu"
            imgItem.setImageResource(thuData.imgThu)
            nameItem.text = thuData.nameThu
            imageViewItem = thuData.imgThu
        }
    }

    override fun onButtonClickedChi(chiData: ChiData, idChi: Int) {

        checkThuChi = idChi
        with(binding){

            typeDanhMuc.text = "Chi"
            imgItem.setImageResource(chiData.imgChi)
            nameItem.text = chiData.nameChi
            imageViewItem = chiData.imgChi
        }
    }

    private fun getDayOfWeek(dateString: String): Int {

        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date: Date = sdf.parse(dateString) ?: return 0
        val calendar = Calendar.getInstance()
        calendar.time = date

        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        return when (dayOfWeek) {

            Calendar.SUNDAY -> 8
            Calendar.MONDAY -> 2
            Calendar.TUESDAY -> 3
            Calendar.WEDNESDAY -> 4
            Calendar.THURSDAY -> 5
            Calendar.FRIDAY -> 6
            Calendar.SATURDAY -> 7
            else -> 1
        }
    }

    private fun checkNgay(dateString: String){

        var check : Int = getDayOfWeek(dateString)

        when(check){

            2 -> {

                binding.txtTh2.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_icon_checked))
                ngayTh2 = mYear.toString() + "-" + (mMonth + 1) + "-" + mDay
                mDayTh2 = mDay
            }

            3 -> {

                binding.txtTh3.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_icon_checked))
                ngayTh2 = mYear.toString() + "-" + (mMonth + 1) + "-" + (mDay - 1)
                mDayTh2 = mDay - 1
            }

            4 -> {

                binding.txtTh4.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_icon_checked))
                ngayTh2 = mYear.toString() + "-" + (mMonth + 1) + "-" + (mDay - 2)
                mDayTh2 = mDay - 2
            }

            5 -> {

                binding.txtTh5.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_icon_checked))
                ngayTh2 = mYear.toString() + "-" + (mMonth + 1) + "-" + (mDay - 3)
                mDayTh2 = mDay - 3
            }

            6 -> {

                binding.txtTh6.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_icon_checked))
                ngayTh2 = mYear.toString() + "-" + (mMonth + 1) + "-" + (mDay - 4)
                mDayTh2 = mDay - 4
            }

            7 -> {

                binding.txtTh7.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_icon_checked))
                ngayTh2 = mYear.toString() + "-" + (mMonth + 1) + "-" + (mDay - 5)
                mDayTh2 = mDay - 5
            }

            8 -> {

                binding.txtCn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_icon_checked))
                ngayTh2 = mYear.toString() + "-" + (mMonth + 1) + "-" + (mDay - 6)
                mDayTh2 = mDay - 6
            }
        }
    }

    private fun showDayofWeek(dateString: String){

        var checkDay = checkMonday(dateString)
        th2 = checkDay

        with(binding){

            // mung 1 vao thu 3
            if (checkDay + 1 <= getDaysInMonth(mYear,mMonth+1)){

                checkDay = checkDay + 1
                th3 = checkDay

            } else {

                checkDay = 1
                th3 = checkDay
                th4 = checkDay+1
                th5 = checkDay+2
                th6 = checkDay+3
                th7 = checkDay+4
                tCn = checkDay+5
            }

            // mung 1 vao thu 4
            if (checkDay + 1 <= getDaysInMonth(mYear,mMonth+1)){

                checkDay = checkDay + 1
                th4 = checkDay

            } else {

                checkDay = 1
                th4 = checkDay
                th5 = checkDay+1
                th6 = checkDay+2
                th7 = checkDay+3
                tCn = checkDay+4
            }

            // mung 1 vao thu 5
            if (checkDay + 1 <= getDaysInMonth(mYear,mMonth+1)){

                checkDay = checkDay + 1
                th5 = checkDay

            } else {

                checkDay = 1
                th5 = checkDay
                th6 = checkDay+1
                th7 = checkDay+2
                tCn = checkDay+3
            }

            // mung 1 vao thu 6
            if (checkDay + 1 <= getDaysInMonth(mYear,mMonth+1)){

                checkDay = checkDay + 1
                th6 = checkDay

            } else {

                checkDay = 1
                th6 = checkDay
                th7 = checkDay+1
                tCn = checkDay+2
            }

            // mung 1 vao thu 7
            if (checkDay + 1 <= getDaysInMonth(mYear,mMonth+1)){

                checkDay = checkDay + 1
                th7 = checkDay

            } else {

                checkDay = 1
                th7 = checkDay
                tCn = checkDay+1
            }

            // mung 1 vao chu nhat
            if (checkDay + 1 <= getDaysInMonth(mYear,mMonth+1)){

                checkDay = checkDay + 1
                tCn = checkDay

            } else {

                checkDay = 1
                tCn = checkDay
            }

            txtNgayTh2.text = th2.toString()
            txtNgayTh3.text = th3.toString()
            txtNgayTh4.text = th4.toString()
            txtNgayTh5.text = th5.toString()
            txtNgayTh6.text = th6.toString()
            txtNgayTh7.text = th7.toString()
            txtNgayCn.text = tCn.toString()
        }
    }

    fun getDaysInMonth(year: Int, month: Int): Int {

        val yearMonth = YearMonth.of(year, month)
        return yearMonth.lengthOfMonth()
    }

    private fun checkMonday(dateString: String) : Int{

        var check : Int = getDayOfWeek(dateString)

        if (check == 2){

            earlyWeek = mDayTh2
        }

        return earlyWeek
    }
}