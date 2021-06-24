package shvyn22.translationapplication.repository

import kotlinx.coroutines.flow.Flow
import shvyn22.translationapplication.data.local.model.TranslationModel
import shvyn22.translationapplication.util.Resource

interface Repository {

    fun getHistoryItems(): Flow<List<TranslationModel>>

    fun translate(translateTo: String, text: String): Flow<Resource<TranslationModel>>
}