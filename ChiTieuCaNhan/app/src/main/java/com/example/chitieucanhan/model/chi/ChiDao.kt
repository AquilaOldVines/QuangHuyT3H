package com.example.chitieucanhan.model.chi

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.chitieucanhan.Const.Const

@Dao
interface ChiDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(chiData: ChiData)

    @Insert
    suspend fun insert(chiData: List<ChiData>)

    @Update
    suspend fun update(chiData: ChiData)

    @Delete
    suspend fun delete(chiData: ChiData)

    @Query("SELECT * FROM ${Const.TBChi.TB_CHI}")
    fun getAll() : LiveData<List<ChiData>>


}