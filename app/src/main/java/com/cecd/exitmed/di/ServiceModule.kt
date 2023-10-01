package com.cecd.exitmed.di

import com.cecd.exitmed.data.service.AuthService
import com.cecd.exitmed.data.service.BookmarkService
import com.cecd.exitmed.data.service.DURService
import com.cecd.exitmed.data.service.MyService
import com.cecd.exitmed.data.service.PillDetailService
import com.cecd.exitmed.data.service.TextSearchService
import com.cecd.exitmed.data.type.BaseUrlType
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Singleton
    @Provides
    fun provideAuthService(
        @NetworkModule.Retrofit2(BaseUrlType.EXIT)
        retrofit: Retrofit
    ): AuthService =
        retrofit.create(AuthService::class.java)

    @Singleton
    @Provides
    fun provideDURService(
        @NetworkModule.Retrofit2(BaseUrlType.DUR)
        retrofit: Retrofit
    ): DURService =
        retrofit.create(DURService::class.java)

    @Singleton
    @Provides
    fun provideTextSearchService(
        @NetworkModule.Retrofit2(BaseUrlType.EXIT)
        retrofit: Retrofit
    ): TextSearchService =
        retrofit.create(TextSearchService::class.java)

    @Singleton
    @Provides
    fun provideBookmarkService(
        @NetworkModule.Retrofit2(BaseUrlType.EXIT)
        retrofit: Retrofit
    ): BookmarkService =
        retrofit.create(BookmarkService::class.java)

    @Singleton
    @Provides
    fun providePillDetailService(
        @NetworkModule.Retrofit2(BaseUrlType.EXIT)
        retrofit: Retrofit
    ): PillDetailService =
        retrofit.create(PillDetailService::class.java)

    @Singleton
    @Provides
    fun provideMyService(
        @NetworkModule.Retrofit2(BaseUrlType.EXIT)
        retrofit: Retrofit
    ): MyService =
        retrofit.create(MyService::class.java)
}
