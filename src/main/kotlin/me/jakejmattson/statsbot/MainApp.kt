package me.jakejmattson.statsbot

import me.aberrantfox.kjdautils.api.startBot

fun main(args: Array<String>) {
    val token = args.firstOrNull() ?: throw IllegalArgumentException("Expecting token as command line argument.")

    startBot(token) {
        configure {
            globalPath = "me.jakejmattson.statsbot"
            prefix = "="
        }
    }
}