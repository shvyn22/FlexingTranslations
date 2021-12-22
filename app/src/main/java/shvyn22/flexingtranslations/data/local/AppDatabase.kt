package shvyn22.flexingtranslations.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import shvyn22.flexingtranslations.data.local.dao.TranslationDao
import shvyn22.flexingtranslations.data.local.model.TranslationModel

@Database(
    entities = [TranslationModel::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun translationDao(): TranslationDao
}