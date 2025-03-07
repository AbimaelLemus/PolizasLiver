package com.example.polizasliver.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.polizasliver.data.database.dao.InfoInsuranceDao
import com.example.polizasliver.data.database.entities.InfoInsuranceEntity

@Database(entities = [InfoInsuranceEntity::class], version = 1)
abstract class InsuranceDatabase: RoomDatabase() {

    abstract fun getInfoInsuranceDao(): InfoInsuranceDao

}