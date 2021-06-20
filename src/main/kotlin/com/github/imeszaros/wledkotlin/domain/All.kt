package com.github.imeszaros.wledkotlin.domain

data class All(

    /** Contains the current state of the light. All values may be modified by the client. */
    var state: State,

    /** Contains general information about the device. No value can be modified using this API. */
    var info: Info,

    /** Contains an array of the effect mode names. */
    var effects: List<String>,

    /** Contains an array of the palette names. */
    var palettes: List<String>)