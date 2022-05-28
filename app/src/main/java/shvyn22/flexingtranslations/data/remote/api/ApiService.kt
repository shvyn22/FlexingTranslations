package shvyn22.flexingtranslations.data.remote.api

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @FormUrlEncoded
    @POST("{translateTo}.json")
    suspend fun translate(
        @Path("translateTo") translateTo: String,
        @Field("text") text: String
    ): ApiResponse
}