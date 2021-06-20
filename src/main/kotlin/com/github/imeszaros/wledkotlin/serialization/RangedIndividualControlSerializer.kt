package com.github.imeszaros.wledkotlin.serialization

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.github.imeszaros.wledkotlin.domain.State.Segment.RangedIndividualControl

class RangedIndividualControlSerializer : StdSerializer<RangedIndividualControl>(RangedIndividualControl::class.java) {

    override fun serialize(value: RangedIndividualControl, gen: JsonGenerator, provider: SerializerProvider) {
        gen.writeStartArray()
        value.colors.forEach { (range, color) ->
            gen.writeNumber(range.first)
            gen.writeNumber(range.last + 1)
            gen.writeObject(color)
        }
        gen.writeEndArray()
    }
}