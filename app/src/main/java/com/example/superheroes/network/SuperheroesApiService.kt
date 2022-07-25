package com.example.superheroes.network


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

private const val BASE_URL =
    "https://us-east-1.aws.data.mongodb-api.com/app/superheroapp-mlqhx/endpoint/"

/**
 * Build the Moshi object with Kotlin adapter factory that Retrofit will be using.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * The Retrofit object with the Moshi converter.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * A public interface that exposes the [getSuperheroes] method
 */
interface SuperheroesApiService {

    /**
     * Returns a [List] of [Superheroes] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "hero" endpoint will be requested with the GET
     * HTTP method
     */
    @Headers("api-key: fvfohlh3dYoKa9hs1XmsdxCrJQ4cxfGUW9BWkluBhTmKfuRrERJ1iywMXqqdNno1")
    @GET("hero")
    suspend fun getSuperheroes(): List<Superheroes>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object SuperheroesApi {
    val retrofitService: SuperheroesApiService by lazy { retrofit.create(SuperheroesApiService::class.java) }
}