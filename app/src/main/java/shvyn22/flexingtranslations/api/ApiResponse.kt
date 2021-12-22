package shvyn22.flexingtranslations.api

import com.google.gson.annotations.SerializedName
import shvyn22.flexingtranslations.data.remote.TranslationDTO

data class ApiResponse(
    @SerializedName("contents")
    val response: TranslationDTO
)
