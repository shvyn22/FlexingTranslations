package shvyn22.flexingtranslations.repository

import kotlinx.coroutines.flow.Flow
import shvyn22.flexingtranslations.data.local.model.TranslationModel
import shvyn22.flexingtranslations.util.Resource

interface Repository {

    fun translate(translateTo: String, text: String): Flow<Resource<TranslationModel>>

    fun getHistoryTranslations(): Flow<List<TranslationModel>>

    suspend fun insertHistoryTranslation(item: TranslationModel)

    suspend fun deleteHistoryTranslation(item: TranslationModel)
}