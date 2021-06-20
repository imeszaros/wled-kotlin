package com.github.imeszaros.wledkotlin

import com.github.imeszaros.wledkotlin.domain.Info
import com.github.imeszaros.wledkotlin.domain.State
import com.github.imeszaros.wledkotlin.domain.All
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WLEDService {

    @GET("json")
    fun all(): Single<All>

    @GET("json/state")
    fun state(): Single<State>

    @GET("json/info")
    fun info(): Single<Info>

    @GET("json/eff")
    fun effects(): Single<List<String>>

    @GET("json/pal")
    fun palettes(): Single<List<String>>

    @POST("json/state")
    fun update(@Body state: State): Single<State>

    fun update(block: State.() -> Unit) = update(State().apply(block))
}