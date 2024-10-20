package com.example.chitieucanhan.model.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.chitieucanhan.Const.Const
import java.io.Serializable
import java.util.Date

@Entity(tableName = Const.TBUser.TB_USER) data class UserData(

    @ColumnInfo(name = Const.TBUser.USER_MONEY_TONG) var moneyTongUser : Float,
    @ColumnInfo(name = Const.TBUser.USER_DATE) var dateuser : Date,
    @ColumnInfo(name = Const.TBUser.USER_MONEY_THU) var moneyThuUser : Float,
    @ColumnInfo(name = Const.TBUser.USER_MONEY_CHI) var moneyChiUser : Float,
    @ColumnInfo(name = Const.TBUser.USER_NOTE) var noteuser : String = "",
    @ColumnInfo(name = Const.TBUser.USER_TYPE) var typeUser : Int,
    @ColumnInfo(name = Const.TBUser.USER_NAME_TYPE) var nameTypeName : String

):Serializable {

    @ColumnInfo(name = Const.TBUser.USER_ID) @PrimaryKey(autoGenerate = true) var idUser : Int = 0
}
