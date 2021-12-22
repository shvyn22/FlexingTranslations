package shvyn22.flexingtranslations.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import shvyn22.flexingtranslations.api.ApiInterface
import shvyn22.flexingtranslations.data.local.dao.TranslationDao
import shvyn22.flexingtranslations.repository.Repository
import shvyn22.flexingtranslations.repository.RepositoryImpl
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