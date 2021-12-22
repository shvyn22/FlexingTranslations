package shvyn22.flexingtranslations.data.remote

import com.google.gson.annotations.SerializedName

data class TranslationDTO(
    @SerializedName("translation")
    val translateTo: String,

    @SerializedName("translated")
    val translation: String,

    @SerializedName("text")
    val text: String
)