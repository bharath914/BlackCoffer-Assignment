package com.example.blackcoffer_assignment.di

import com.example.blackcoffer_assignment.data.PersonsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePersonDatabase()= PersonsDatabase()

}