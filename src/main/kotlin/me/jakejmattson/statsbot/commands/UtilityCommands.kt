package me.jakejmattson.statsbot.commands

import com.sun.org.apache.xml.internal.serializer.utils.Utils.messages
import me.aberrantfox.kjdautils.api.dsl.command.*
import me.aberrantfox.kjdautils.extensions.stdlib.toMinimalTimeString
import java.awt.Color
import java.util.Date

private val startTime = Date()

@CommandSet("Utility")
fun utilityCommands() = commands {
    command("Ping") {
        description = "Display the network ping of the bot."
        execute { event ->
            event.discord.jda.restPing.queue {
                event.respond("Ping: ${it}ms\n")
            }
        }
    }

    command("Uptime") {
        description = "Displays how long the bot has been running."
        execute {
            val seconds = (Date().time - startTime.time) / 1000

            it.respond {
                title = "I have been running since"
                description = startTime.toString()
                color = Color(0x00bfff)

                field {
                    name = "That's been"
                    value = seconds.toMinimalTimeString()
                }
            }
        }
    }
}