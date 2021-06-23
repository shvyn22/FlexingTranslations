package shvyn22.translationapplication.api

import com.google.gson.annotations.SerializedName
import shvyn22.translationapplication.data.remote.TranslationDTO

data class ApiResponse(
    @SerializedName("contents")
    val translation: TranslationDTO
)
