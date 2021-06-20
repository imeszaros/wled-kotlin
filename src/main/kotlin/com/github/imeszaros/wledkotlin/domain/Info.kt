package com.github.imeszaros.wledkotlin.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class Info(

    /** Version name. */
    @JsonProperty("ver")
    var version: String,

    /** Build ID (YYMMDDB, B = daily build index). */
    @JsonProperty("vid")
    var buildId: Int,

    /** Contains info about the LED setup. */
    var leds: LEDs,

    /** If true, an UI with only a single button for toggling sync should toggle receive+send, otherwise send only. */
    var str: Boolean,

    /** Friendly name of the light. Intended for display in lists and titles. */
    var name: String,

    /** The UDP port for realtime packets and WLED broadcast. */
    @JsonProperty("udpport")
    var udpPort: Int,

    /** If true, the software is currently receiving realtime data via UDP or E1.31. */
    var live: Boolean,

    /** Info about the realtime data source. */
    var lm: String,

    /** Realtime data source IP address. */
    var lip: String,

    /** Number of currently connected WebSockets clients. -1 indicates that WS is unsupported in this build. */
    @JsonProperty("ws")
    var webSockets: Int,

    /** Number of effects included. */
    @JsonProperty("fxcount")
    var effectCount: Int,

    /** Number of palettes configured. */
    @JsonProperty("palcount")
    var paletteCount: Int,

    /** Info about current signal strength. */
    var wifi: Wifi,

    /** Info about the embedded LittleFS filesystem. */
    @JsonProperty("fs")
    var fileSystem: FileSystem,

    /** Name of the platform. */
    @JsonProperty("arch")
    var architecture: String,

    /** Version of the underlying (Arduino core) SDK. */
    var core: String,

    /** Bytes of heap memory (RAM) currently available. Problematic if <10k. */
    @JsonProperty("freeheap")
    var freeHeap: Int,

    /** Time since the last boot/reset in seconds. */
    var uptime: Int,

    /** Used for debugging purposes only. */
    var opt: Int,

    /** The producer/vendor of the light. Always WLED for standard installations. */
    var brand: String,

    /** The product name. Always FOSS for standard installations. */
    var product: String,

    /** The hexadecimal hardware MAC address of the light, lowercase and without colons. */
    @JsonProperty("mac")
    var macAddress: String) {

    data class LEDs(

        /** Total LED count. */
        var count: Int,

        /** Current frames per second. (available since 0.12.0). */
        var fps: Int,

        /** True if LEDs are 4-channel (RGBW). */
        var rgbw: Boolean,

        /** True if a white channel slider should be displayed. (available since at least 0.11.1). */
        @JsonProperty("wv")
        var whiteVisible: Boolean,

        /** Current LED power usage in milliamps as determined by the ABL. 0 if ABL is disabled. */
        @JsonProperty("pwr")
        var power: Int,

        /** Maximum power budget in milliamps for the ABL. 0 if ABL is disabled. */
        @JsonProperty("maxpwr")
        var maxPower: Int,

        /** Maximum number of segments supported by this version. */
        @JsonProperty("maxseg")
        var maxSegments: Int)

    data class Wifi(

        /** The BSSID of the currently connected network. */
        var bssid: String,

        /** Relative signal quality of the current connection. */
        var signal: Int,

        /** The current WiFi channel. */
        var channel: Int)

    data class FileSystem(

        /** Estimate of used filesystem space in kilobytes. */
        @JsonProperty("u")
        var used: Int,

        /** Total filesystem size in kilobytes. */
        @JsonProperty("t")
        var total: Int,

        /** Unix timestamp for the last modification to the presets.json file. Not accurate after boot or after using /edit. */
        @JsonProperty("pmt")
        var presetsLastModified: Int)
}