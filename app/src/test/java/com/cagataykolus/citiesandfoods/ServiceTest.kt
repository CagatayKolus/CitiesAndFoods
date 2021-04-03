package com.cagataykolus.citiesandfoods

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cagataykolus.citiesandfoods.data.remote.api.NpointService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.core.IsNull.notNullValue
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Çağatay Kölüş on 3.04.2021.
 * cagataykolus@gmail.com
 */

@RunWith(JUnit4::class)
class ServiceTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: NpointService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
            .create(NpointService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun getCitiesTest() = runBlocking {
        enqueueResponse("cities.json")
        val cities = service.getCities().body()

        assertThat(cities, notNullValue())
        assertThat(cities!!.size, `is`(3))
        assertThat(cities[0].name, `is`("Tokyo"))
    }

    @Test
    fun getFoodsTest() = runBlocking {
        enqueueResponse("foods.json")
        val foods = service.getFoods().body()

        assertThat(foods, notNullValue())
        assertThat(foods!!.size, `is`(4))
        assertThat(foods[0].name, `is`("Sushi"))
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader!!
            .getResourceAsStream("response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse
                .setBody(source.readString(Charsets.UTF_8))
        )
    }
}
