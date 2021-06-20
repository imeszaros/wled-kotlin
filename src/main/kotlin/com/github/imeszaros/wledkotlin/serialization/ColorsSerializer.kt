package com.github.imeszaros.wledkotlin.serialization

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.github.imeszaros.wledkotlin.domain.State.Segment.Colors

class ColorsSerializer : StdSerializer<Colors>(Colors::class.java) {

    override fun serialize(value: Colors, gen: JsonGenerator, provider: SerializerProvider) {
        gen.writeStartArray()
        value.primary?.let(gen::writeObject)
        value.secondary?.let(gen::writeObject)
        value.tertiary?.let(gen::writeObject)
        gen.writeEndArray()
    }
}