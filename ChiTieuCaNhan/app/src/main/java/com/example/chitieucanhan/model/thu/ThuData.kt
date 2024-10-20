package com.example.chitieucanhan.model.thu

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.chitieucanhan.Const.Const

@Entity(tableName = Const.TBThu.TB_THU) data class ThuData(

    @ColumnInfo(name = Const.TBThu.THU_NAME) var nameThu : String = "",
    @ColumnInfo(name = Const.TBThu.THU_IMG) var imgThu : Int = 0
){

    @ColumnInfo(name = Const.TBThu.THU_ID) @PrimaryKey(autoGenerate = true) var idThu : Int = 0
}
