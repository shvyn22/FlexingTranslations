package shvyn22.translationapplication.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "History")
data class TranslationModel(

    @PrimaryKey
    @ColumnInfo(name = "text")
    val text: String,

    @ColumnInfo(name = "translateTo")
    val translateTo: String,

    @ColumnInfo(name = "translation")
    val translation: String
)
