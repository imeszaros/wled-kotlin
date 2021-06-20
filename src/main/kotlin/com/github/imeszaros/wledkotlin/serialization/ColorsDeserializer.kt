package com.github.imeszaros.wledkotlin.serialization

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonToken
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.imeszaros.wledkotlin.domain.State.Segment.Colors
import com.github.imeszaros.wledkotlin.domain.State.Color

class ColorsDeserializer : StdDeserializer<Colors>(Colors::class.java) {

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Colors {
        if (p.currentToken != JsonToken.START_ARRAY) throw JsonParseException(p, "Array start expected.")

        p.nextToken()

        val primary = if (p.currentToken != JsonToken.END_ARRAY) {
            p.readValueAs(Color::class.java).also {
                p.nextToken()
            }
        } else {
            null
        }

        val secondary = if (p.currentToken != JsonToken.END_ARRAY) {
            p.readValueAs(Color::class.java).also {
                p.nextToken()
            }
        } else {
            null
        }

        val tertiary = if (p.currentToken != JsonToken.END_ARRAY) {
            p.readValueAs(Color::class.java).also {
                p.nextToken()
            }
        } else {
            null
        }

        if (p.currentToken != JsonToken.END_ARRAY) throw JsonParseException(p, "Array end expected.")

        return Colors(primary, secondary, tertiary)
    }
}