package shvyn22.flexingtranslations.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import shvyn22.flexingtranslations.api.ApiInterface
import shvyn22.flexingtranslations.data.local.dao.TranslationDao
import shvyn22.flexingtranslations.data.local.model.TranslationModel
import shvyn22.flexingtranslations.util.Resource
import shvyn22.flexingtranslations.util.fromDTOToModel

class RepositoryImpl(
    private val api: ApiInterface,
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
            insert(item)
            emit(Resource.Success(item))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: ""))
        }
    }

    override fun getHistoryItems(): Flow<List<TranslationModel>> =
        translationDao.getAll()

    override suspend fun insert(item: TranslationModel) = translationDao.insert(item)

    override suspend fun delete(item: TranslationModel) = translationDao.delete(item)
}