package com.example.datastoresample.di

import android.content.Context
import com.example.datastoresample.data.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun providesDataStore(
        @ApplicationContext context: Context
    ) : DataRepository = DataRepository(context)


}