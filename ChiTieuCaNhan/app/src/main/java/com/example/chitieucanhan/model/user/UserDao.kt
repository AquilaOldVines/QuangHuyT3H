package com.example.chitieucanhan.model.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.chitieucanhan.Const.Const
import java.util.Date

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(userData: UserData)

    @Insert
    suspend fun insert(userData: List<UserData>)

    @Update
    suspend fun update(userData: UserData)

    @Delete
    suspend fun delete(userData: UserData)

    @Query("SELECT * FROM ${Const.TBUser.TB_USER}")
    fun getAll() : LiveData<List<UserData>>

    @Query("SELECT * FROM ${Const.TBUser.TB_USER} WHERE ${Const.TBUser.USER_DATE} = :date")
    fun getAllDate(date : Date) : LiveData<List<UserData>>

    @Query("SELECT * FROM ${Const.TBUser.TB_USER} WHERE ${Const.TBUser.USER_NAME_TYPE} = :name")
    fun getAllName(name : String) : LiveData<List<UserData>>
}