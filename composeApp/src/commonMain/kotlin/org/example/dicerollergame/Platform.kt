package org.example.dicerollergame

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform