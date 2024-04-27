package io.github.ryunen344.droidkaigies

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform