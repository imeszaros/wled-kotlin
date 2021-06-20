package com.github.imeszaros.wledkotlin

class WLEDClient private constructor(service: WLEDService) : WLEDService by service {

    constructor(hostname: String) : this(RetrofitFactory.create(hostname).build().create(WLEDService::class.java))
}