package com.github.imeszaros.wledkotlin

import com.fasterxml.jackson.databind.ObjectMapper
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

object RetrofitFactory {

    fun create(hostname: String, objectMapper: ObjectMapper = ObjectMapperFactory.create().build()): Retrofit.Builder = Retrofit.Builder()
        .baseUrl("http://$hostname/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(JacksonConverterFactory.create(objectMapper))
}