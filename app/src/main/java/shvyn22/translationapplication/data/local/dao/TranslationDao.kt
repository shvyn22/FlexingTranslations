package shvyn22.translationapplication.data.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import shvyn22.translationapplication.data.local.model.TranslationModel

interface TranslationDao {

    @Query("SELECT * FROM History")
    fun getAll(): Flow<TranslationModel>

    @Insert
    fun insert(item: TranslationModel)

    @Delete
    fun delete(item: TranslationModel)
}