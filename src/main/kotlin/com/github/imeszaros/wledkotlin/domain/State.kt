package com.github.imeszaros.wledkotlin.domain

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.github.imeszaros.wledkotlin.serialization.*

@WLEDDSL
data class State(

    /** On/Off state of the light. */
    var on: Boolean? = null,

    /** Brightness of the light. If on is false, contains last brightness when light was on (aka brightness when on is set to true). Setting bri to 0 is supported but it is recommended to use the range 1-255 and use on: false to turn off. The state response will never have the value 0 for bri. */
    @JsonProperty("bri")
    var brightness: Int? = null,

    /** Duration of the crossfade between different colors/brightness levels. One unit is 100ms, so a value of 4 results in a transition of 400ms. */
    @JsonProperty("transition")
    var transitionSpeed: Int? = null,

    /** Similar to transition speed, but applies to just the current API call. Not included in state response. */
    @JsonProperty("tt")
    var transitionSpeedOnce: Int? = null,

    /** ID of currently set preset. */
    @JsonProperty("ps")
    var preset: Int? = null,

    /** Save current light config to specified preset slot. Not included in state response. */
    @JsonProperty("psave")
    var saveToPreset: Int? = null,

    /** ID of currently set playlist. For now, this sets the preset cycle feature, -1 is off and 0 is on. */
    @JsonProperty("pl")
    var playlistId: Int? = null,

    /** Nightlight configuration. */
    @JsonProperty("nl")
    var nightlight: Nightlight? = null,

    /** UDP notification settings. */
    @JsonProperty("udpn")
    var udpNotification: UDPNotification? = null,

    /** If set to true in a JSON POST command, the response will contain the full JSON state object. Not included in state response. */
    @JsonProperty("v")
    var verboseResponse: Boolean? = null,

    /** If set to true, device will reboot immediately. Not included in state response. */
    @JsonProperty("rb")
    var reboot: Boolean? = null,

    /** Live data override. 0 is off, 1 is override until live data ends, 2 is override until ESP reboot (available since 0.10.0) */
    @JsonProperty("lor")
    var liveDataOverride: Int? = null,

    /** Set module time to unix timestamp. Not included in state response. */
    var time: Int? = null,

    /** Main segment index. */
    @JsonProperty("mainseg")
    var mainSegment: Int? = null,

    /** Segments are individual parts of the LED strip. In 0.9.0 this will enables running different effects on different parts of the strip. */
    @JsonProperty("seg")
    var segments: MutableList<Segment>? = null,

    /** Custom preset playlists. Not included in state response (available since 0.11.0). */
    var playlist: Playlist? = null) {

    constructor(block: State.() -> Unit) : this() {
        apply(block)
    }

    fun nightlight(block: Nightlight.() -> Unit) {
        nightlight = Nightlight().apply(block)
    }

    fun udpNotification(block: UDPNotification.() -> Unit) {
        udpNotification = UDPNotification().apply(block)
    }

    fun segment(block: Segment.() -> Unit) {
        if (segments == null) {
            segments = ArrayList()
        }
        segments!! += Segment().apply(block)
    }

    fun playlist(block: Playlist.() -> Unit) {
        playlist = Playlist().apply(block)
    }

    @WLEDDSL
    @JsonSerialize(using = ColorSerializer::class)
    @JsonDeserialize(using = ColorDeserializer::class)
    data class Color(var r: Int, var g: Int, var b: Int, var w: Int? = null)

    @WLEDDSL
    data class Nightlight(

        /** Nightlight currently active. */
        var on: Boolean? = null,

        /** Duration of nightlight in minutes: 1 to 255 */
        @JsonProperty("dur")
        var duration: Int? = null,

        /** Nightlight mode (0: instant, 1: fade, 2: color fade, 3: sunrise) (available since 0.10.2) */
        var mode: Mode? = null,

        /** Target brightness of nightlight feature. */
        @JsonProperty("tbri")
        var targetBrightness: Int? = null) {

        enum class Mode {

            Instant, Fade, ColorFade, Sunrise;

            @JsonValue
            fun serialize() = ordinal
        }
    }

    @WLEDDSL
    data class UDPNotification(

        /** Send WLED broadcast (UDP sync) packet on state change. */
        var send: Boolean? = null,

        /** Receive broadcast packets. */
        @JsonProperty("recv")
        var receive: Boolean? = null,

        /** Don't send a broadcast packet (applies to just the current API call). Not included in state response. */
        @JsonProperty("nn")
        var skipNotificationOnce: Boolean? = null)

    @WLEDDSL
    data class Segment(

        /** Zero-indexed ID of the segment. May be omitted, in that case the ID will be inferred from the order of the segment objects in the seg array. As such, not included in state response. */
        var id: Int? = null,

        /** LED the segment starts at. */
        var start: Int? = null,

        /** LED the segment stops at, not included in range. If stop is set to a lower or equal value than start (setting to 0 is recommended), the segment is invalidated and deleted. */
        var stop: Int? = null,

        /** Length of the segment (stop - start). stop has preference, so if it is included, length is ignored. */
        @JsonProperty("len")
        var length: Int? = null,

        /** Array that has up to 3 color arrays as elements, the primary, secondary (background) and tertiary colors of the segment. Each color is an array of 3 or 4 bytes, which represent an RGB(W) color. */
        @JsonProperty("col")
        var colors: Colors? = null,

        /** ID of the effect. */
        @JsonProperty("fx")
        var effect: Int? = null,

        /** Relative effect speed: 0 to 255 */
        @JsonProperty("sx")
        var effectSpeed: Int? = null,

        /** Effect intensity: 0 to 255 */
        @JsonProperty("ix")
        var effectIntensity: Int? = null,

        /** ID of the color palette. */
        @JsonProperty("pal")
        var palette: Int? = null,

        /** True if the segment is selected. Selected segments will have their state (color/FX) updated by APIs that don't support segments (currently any API except this JSON API). If no segment is selected, the first segment (id:0) will behave as if selected. WLED will report the state of the first (lowest id) segment that is selected to APIs (UDP sync, HTTP, MQTT, Blynk...). */
        @JsonProperty("sel")
        var selected: Boolean? = null,

        /** Flips the segment, causing animations to change direction. */
        @JsonProperty("rev")
        var reversed: Boolean? = null,

        /** Turns on and off the individual segment. (available since 0.10.0) */
        var on: Boolean? = null,

        /** Sets the individual segment brightness: 0 to 255 (available since 0.10.0) */
        @JsonProperty("bri")
        var brightness: Int? = null,

        /** Mirrors the segment. (available since 0.10.2) */
        @JsonProperty("mi")
        var mirror: Boolean? = null,

        /** Individual LED control. Not included in state response (available since 0.10.2) */
        @JsonProperty("i")
        var individualControl: IndividualControl? = null) {

        fun colors(block: Colors.() -> Unit) {
            colors = Colors().apply(block)
        }

        @WLEDDSL
        @JsonSerialize(using = ColorsSerializer::class)
        @JsonDeserialize(using = ColorsDeserializer::class)
        data class Colors(

            /** Primary color. */
            var primary: Color? = null,

            /** Secondary color. */
            var secondary: Color? = null,

            /** Tertiary color. */
            var tertiary: Color? = null)

        sealed interface IndividualControl

        @WLEDDSL
        @JsonSerialize(using = ArrayIndividualControlSerializer::class)
        data class ArrayIndividualControl(var colors: MutableList<Color> = ArrayList()) : IndividualControl {

            constructor(vararg colors: Color) : this(mutableListOf(*colors))
        }

        @WLEDDSL
        @JsonSerialize(using = IndexedIndividualControlSerializer::class)
        data class IndexedIndividualControl(var colors: MutableMap<Int, Color> = LinkedHashMap()) : IndividualControl {

            constructor(vararg pairs: Pair<Int, Color>) : this(mutableMapOf(*pairs))
        }

        @WLEDDSL
        @JsonSerialize(using = RangedIndividualControlSerializer::class)
        data class RangedIndividualControl(var colors: MutableMap<IntRange, Color> = LinkedHashMap()) :
            IndividualControl {

            constructor(vararg pairs: Pair<IntRange, Color>) : this(mutableMapOf(*pairs))
        }
    }

    @WLEDDSL
    data class Playlist(

        /** Array of preset ID integers to be applied in this order. */
        @JsonProperty("ps")
        var presets: MutableList<Int>? = null,

        /** Array of time each preset should be kept, in tenths of seconds. If only one integer is supplied, all presets will be kept for that time. Defaults to 10 seconds if not provided. */
        @JsonProperty("dur")
        var durations: MutableList<Int>? = null,

        /** Array of time each preset should transition to the next one, in tenths of seconds. Defaults to the current transition time if not provided. */
        @JsonProperty("transition")
        var transitions: MutableList<Int>? = null,

        /** How many times the entire playlist should cycle before finishing. Set to 0 for an indefinite cycle. Default to indefinite if not provided. */
        var repeat: Int?  = null,

        /** Single preset ID to apply after the playlist finished. Has no effect when an indefinite cycle is set. If not provided, the light will stay on the last preset of the playlist. */
        var end: Int?  = null)
}