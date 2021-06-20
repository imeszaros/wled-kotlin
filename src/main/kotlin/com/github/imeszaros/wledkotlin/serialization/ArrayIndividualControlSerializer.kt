package com.github.imeszaros.wledkotlin.serialization

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.github.imeszaros.wledkotlin.domain.State.Segment.ArrayIndividualControl

class ArrayIndividualControlSerializer : StdSerializer<ArrayIndividualControl>(ArrayIndividualControl::class.java) {

    override fun serialize(value: ArrayIndividualControl, gen: JsonGenerator, provider: SerializerProvider) {
        gen.writeStartArray()
        value.colors.forEach(gen::writeObject)
        gen.writeEndArray()
    }
}