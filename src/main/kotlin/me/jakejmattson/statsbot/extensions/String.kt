package me.jakejmattson.statsbot.extensions

inline fun wrapCode(builderAction: StringBuilder.() -> Unit) =
    buildString {
        appendln("```")
        apply(builderAction)
        appendln("```")
    }