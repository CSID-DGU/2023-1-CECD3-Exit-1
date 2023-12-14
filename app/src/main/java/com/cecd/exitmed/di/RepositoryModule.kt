package com.cecd.exitmed.di

import com.cecd.exitmed.data.repository.AuthRepositoryImpl
import com.cecd.exitmed.data.repository.BookmarkRepositoryImpl
import com.cecd.exitmed.data.repository.DURRepositoryImpl
import com.cecd.exitmed.data.repository.DoseRepositoryImpl
import com.cecd.exitmed.data.repository.ImageSearchRepositoryImpl
import com.cecd.exitmed.data.repository.PillDetailRepositoryImpl
import com.cecd.exitmed.data.repository.TextSearchRepositoryImpl
import com.cecd.exitmed.domain.repository.AuthRepository
import com.cecd.exitmed.domain.repository.BookmarkRepository
import com.cecd.exitmed.domain.repository.DURRepository
import com.cecd.exitmed.domain.repository.DoseRepository
import com.cecd.exitmed.domain.repository.ImageSearchRepository
import com.cecd.exitmed.domain.repository.PillDetailRepository
import com.cecd.exitmed.domain.repository.TextSearchRepository
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

    @Binds
    @Singleton
    fun bindTextSearchRepository(
        textSearchRepositoryImpl: TextSearchRepositoryImpl
    ): TextSearchRepository

    @Binds
    @Singleton
    fun bindBookmarkRepository(
        bookmarkRepositoryImpl: BookmarkRepositoryImpl
    ): BookmarkRepository

    @Binds
    @Singleton
    fun bindPillDetailRepository(
        pillDetailRepositoryImpl: PillDetailRepositoryImpl
    ): PillDetailRepository

    @Binds
    @Singleton
    fun bindMyRepository(
        doseRepositoryImpl: DoseRepositoryImpl
    ): DoseRepository

    @Binds
    @Singleton
    fun bindImageSearchRepository(
        imageSearchRepositoryImpl: ImageSearchRepositoryImpl
    ): ImageSearchRepository
}
