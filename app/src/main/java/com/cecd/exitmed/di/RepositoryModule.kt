package com.cecd.exitmed.di

import com.cecd.exitmed.data.repository.AuthRepositoryImpl
import com.cecd.exitmed.data.repository.DURRepositoryImpl
import com.cecd.exitmed.domain.repository.AuthRepository
import com.cecd.exitmed.domain.repository.DURRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    fun bindDURRepository(
        durRepositoryImpl: DURRepositoryImpl
    ): DURRepository
}
