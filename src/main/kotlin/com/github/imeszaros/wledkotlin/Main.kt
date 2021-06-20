package com.github.imeszaros.wledkotlin

import com.github.imeszaros.wledkotlin.domain.State

fun main() {
    val client = WLEDClient("192.168.178.56")

    client.update {
        playlist {
            presets = mutableListOf(2)
            durations = mutableListOf(100)
            transitions = mutableListOf(0)
            repeat = 1
            end = 1
        }
    }.subscribe()

    client.update {
        segment {
            colors {
                primary = State.Color(255, 0, 0)
            }
        }
    }
}