package shvyn22.flexingtranslations.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import shvyn22.flexingtranslations.data.local.model.TranslationModel

@Dao
interface TranslationDao {

    @Query("SELECT * FROM History ORDER BY id DESC")
    fun getAll(): Flow<List<TranslationModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: TranslationModel)

    @Delete
    suspend fun delete(item: TranslationModel)
}