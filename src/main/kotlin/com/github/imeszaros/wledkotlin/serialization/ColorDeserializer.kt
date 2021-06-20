package com.github.imeszaros.wledkotlin.serialization

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonToken
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.imeszaros.wledkotlin.domain.State.Color

class ColorDeserializer : StdDeserializer<Color>(Color::class.java) {

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Color {
        if (p.currentToken != JsonToken.START_ARRAY) throw JsonParseException(p, "Array start expected.")

        p.nextToken()
        val r = p.intValue

        p.nextToken()
        val g = p.intValue

        p.nextToken()
        val b = p.intValue

        p.nextToken()
        val w = if (p.currentToken == JsonToken.VALUE_NUMBER_INT) {
            p.intValue.also {
                p.nextToken()
            }
        } else {
            null
        }

        if (p.currentToken != JsonToken.END_ARRAY) throw JsonParseException(p, "Array end expected.")

        return Color(r, g, b, w)
    }
}