package shvyn22.translationapplication.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import shvyn22.translationapplication.api.ApiInterface
import shvyn22.translationapplication.data.local.dao.TranslationDao
import shvyn22.translationapplication.repository.Repository
import shvyn22.translationapplication.repository.RepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        apiInterface: ApiInterface,
        translationDao: TranslationDao
    ): Repository = RepositoryImpl(apiInterface, translationDao)
}