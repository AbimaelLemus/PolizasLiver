package com.example.polizasliver.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.polizasliver.data.database.entities.InfoInsuranceEntity

@Dao
interface InfoInsuranceDao {

    @Query("SELECT * FROM info_insurance ORDER BY id DESC")
    suspend fun getAllInfoInsurance(): List<InfoInsuranceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInfoInsurance(infoInsurance: List<InfoInsuranceEntity>)
}