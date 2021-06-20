package com.github.imeszaros.wledkotlin.serialization

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.github.imeszaros.wledkotlin.domain.State.Segment.IndexedIndividualControl

class IndexedIndividualControlSerializer : StdSerializer<IndexedIndividualControl>(IndexedIndividualControl::class.java) {

    override fun serialize(value: IndexedIndividualControl, gen: JsonGenerator, provider: SerializerProvider) {
        gen.writeStartArray()
        value.colors.forEach { (index, color) ->
            gen.writeNumber(index)
            gen.writeObject(color)
        }
        gen.writeEndArray()
    }
}