package com.example.chitieucanhan.model.chi

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.chitieucanhan.Const.Const

@Entity(tableName = Const.TBChi.TB_CHI) data class ChiData(

    @ColumnInfo(name = Const.TBChi.CHI_NAME) var nameChi : String = "",
    @ColumnInfo(name = Const.TBChi.CHI_IMG) var imgChi : Int = 0
){

    @ColumnInfo(name = Const.TBChi.CHI_ID) @PrimaryKey(autoGenerate = true) var idChi : Int = 0
}
