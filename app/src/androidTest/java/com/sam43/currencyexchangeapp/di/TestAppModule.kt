package com.sam43.currencyexchangeapp.di

import android.content.Context
import androidx.room.Room
import com.sam43.currencyexchangeapp.data.local.AppDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.junit.Assert.*

import org.junit.Before
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Named(AppDB.DATABASE_NAME)
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, AppDB::class.java)
            .allowMainThreadQueries()
            .build()
}