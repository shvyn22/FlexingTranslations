package shvyn22.flexingtranslations.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import shvyn22.flexingtranslations.data.local.model.TranslationModel

@Dao
interface TranslationDao {

    @Query("SELECT * FROM History ORDER BY id DESC")
    fun getHistoryTranslations(): Flow<List<TranslationModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistoryTranslation(item: TranslationModel)

    @Delete
    suspend fun deleteHistoryTranslation(item: TranslationModel)
}