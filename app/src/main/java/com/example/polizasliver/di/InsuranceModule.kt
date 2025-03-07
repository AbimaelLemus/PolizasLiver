package com.example.polizasliver.di

import android.content.Context
import androidx.room.Room
import com.example.polizasliver.data.database.InsuranceDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InsuranceModule {
    private const val INSURANCE_DATABASE = "insurance_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room
            .databaseBuilder(
                context,
                InsuranceDatabase::class.java,
                INSURANCE_DATABASE
            ).build()

    @Singleton
    @Provides
    fun provideInsuranceDao(db: InsuranceDatabase) = db.getInfoInsuranceDao()
}