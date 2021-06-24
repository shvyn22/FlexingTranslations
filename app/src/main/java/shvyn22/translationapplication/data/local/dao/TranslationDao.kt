package shvyn22.translationapplication.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import shvyn22.translationapplication.data.local.model.TranslationModel

@Dao
interface TranslationDao {

    @Query("SELECT * FROM History ORDER BY id DESC")
    fun getAll(): Flow<List<TranslationModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: TranslationModel)
}