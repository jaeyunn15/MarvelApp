package com.project.marvelapp.di

import com.project.marvelapp.datasource.local.UserPrefDataSource
import com.project.marvelapp.datasource.local.UserPrefDataSourceImpl
import com.project.marvelapp.datasource.remote.CharacterDataSource
import com.project.marvelapp.datasource.remote.CharacterDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindsCharacterDataSource(
        characterDataSource: CharacterDataSourceImpl
    ): CharacterDataSource

    @Binds
    fun bindsUserPrefDataSource(
        userPrefDataSource: UserPrefDataSourceImpl
    ): UserPrefDataSource
}