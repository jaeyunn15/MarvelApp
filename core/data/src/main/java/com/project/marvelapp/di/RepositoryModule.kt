package com.project.marvelapp.di

import com.project.marvelapp.repository.CharacterRepository
import com.project.marvelapp.repository.CharacterRepositoryImpl
import com.project.marvelapp.repository.UserPrefRepository
import com.project.marvelapp.repository.UserPrefRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsCharacterRepository (
        characterRepository: CharacterRepositoryImpl
    ): CharacterRepository

    @Binds
    fun bindsUserPrefRepository (
        userPrefRepository: UserPrefRepositoryImpl
    ):  UserPrefRepository
}