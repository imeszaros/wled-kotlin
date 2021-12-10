Kotlin API for WLED
=

This is a very simple and thin Kotlin API for controlling devices running
the awesome [WLED](https://github.com/Aircoookie/WLED) firmware.

Usage
-

Include the library in your build:
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.imeszaros:wled-kotlin:v0.6'
}
```

Instantiate and call the API:
```kotlin
fun main() {
    val client = WLEDClient("ip address or hostname of of WLED device")

    client.update {
        segment {
            colors {
                primary = Color(255, 0, 0)
            }
        }
    }.subscribe()
}
```

For API description, see: [https://github.com/Aircoookie/WLED/wiki/JSON-API](https://github.com/Aircoookie/WLED/wiki/JSON-API)
