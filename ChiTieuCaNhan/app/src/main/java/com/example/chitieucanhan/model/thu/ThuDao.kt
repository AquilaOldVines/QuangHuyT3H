package com.example.chitieucanhan.model.thu

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.chitieucanhan.Const.Const

@Dao
interface ThuDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(thuData: ThuData)

    @Insert
    suspend fun insert(thuData: List<ThuData>)

    @Update
    suspend fun update(thuData: ThuData)

    @Delete
    suspend fun delete(thuData: ThuData)

    @Query("SELECT * FROM ${Const.TBThu.TB_THU}")
    fun getAll() : LiveData<List<ThuData>>
}