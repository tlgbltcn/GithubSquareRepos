package com.tlgbltcn.githubsquarerepos.data.remote

import com.google.common.truth.Truth
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.tlgbltcn.githubsquarerepos.data.model.RepositoryItem
import com.tlgbltcn.githubsquarerepos.util.TokenInterceptor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Test
import retrofit2.Retrofit
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
@ExperimentalSerializationApi
class GithubServiceTest {

    private val mockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(50, TimeUnit.SECONDS)
        .readTimeout(50, TimeUnit.SECONDS)
        .writeTimeout(50, TimeUnit.SECONDS)
        .addInterceptor(TokenInterceptor())
        .build()

    private val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    private val api = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
        .create(GithubService::class.java)


    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should fetch movies correctly given 200 response`() = runBlocking {
        // Given
        mockWebServer.enqueueResponse("response.json", 200)

        // When
        val actual = api.getRepos()

        // Then
        Truth.assertThat(actual.body()).isEqualTo(expected)
    }

    private fun MockWebServer.enqueueResponse(fileName: String, code: Int) {
        javaClass.classLoader?.getResourceAsStream(fileName)
            ?.source()
            ?.buffer()
            .also { source ->
                source?.run {
                    enqueue(
                        MockResponse()
                            .setResponseCode(code)
                            .setBody(source.readString(StandardCharsets.UTF_8))
                    )
                }
            }
    }
}

val expected = listOf(
    RepositoryItem(
        contributorsUrl = null,
        deploymentsUrl = null,
        description = null,
        downloadsUrl = null,
        forksCount = null,
        gitUrl = null,
        id = null,
        isTemplate = null,
        language = null,
        name = "",
        size = null,
        stargazersCount = null,
        stargazersUrl = null,
        updatedAt = null,
        url = null,
        watchersCount = null,
        isBookmarked = false
    )
)