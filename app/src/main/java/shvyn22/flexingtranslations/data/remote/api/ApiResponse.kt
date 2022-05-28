package shvyn22.flexingtranslations.data.remote.api

import com.google.gson.annotations.SerializedName
import shvyn22.flexingtranslations.data.remote.dto.TranslationDTO

data class ApiResponse(
    @SerializedName("contents")
    val response: TranslationDTO
)
