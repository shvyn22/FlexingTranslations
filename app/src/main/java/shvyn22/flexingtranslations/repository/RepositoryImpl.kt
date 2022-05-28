package shvyn22.flexingtranslations.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import shvyn22.flexingtranslations.data.local.dao.TranslationDao
import shvyn22.flexingtranslations.data.local.model.TranslationModel
import shvyn22.flexingtranslations.data.remote.api.ApiService
import shvyn22.flexingtranslations.data.util.fromDTOToModel
import shvyn22.flexingtranslations.util.Resource

class RepositoryImpl(
    private val api: ApiService,
    private val translationDao: TranslationDao
) : Repository {

    override fun translate(
        translateTo: String,
        text: String
    ): Flow<Resource<TranslationModel>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.translate(translateTo, text)
            val item = fromDTOToModel(response.response)
            insertHistoryTranslation(item)
            emit(Resource.Success(item))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: ""))
        }
    }

    override fun getHistoryTranslations(): Flow<List<TranslationModel>> =
        translationDao.getHistoryTranslations()

    override suspend fun insertHistoryTranslation(item: TranslationModel) =
        translationDao.insertHistoryTranslation(item)

    override suspend fun deleteHistoryTranslation(item: TranslationModel) =
        translationDao.deleteHistoryTranslation(item)
}