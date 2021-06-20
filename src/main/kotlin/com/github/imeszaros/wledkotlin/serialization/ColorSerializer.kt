package com.github.imeszaros.wledkotlin.serialization

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.github.imeszaros.wledkotlin.domain.State.Color

class ColorSerializer : StdSerializer<Color>(Color::class.java) {

    override fun serialize(value: Color, gen: JsonGenerator, provider: SerializerProvider) {
        gen.writeStartArray()
        gen.writeNumber(value.r)
        gen.writeNumber(value.g)
        gen.writeNumber(value.b)
        value.w?.let(gen::writeNumber)
        gen.writeEndArray()
    }
}