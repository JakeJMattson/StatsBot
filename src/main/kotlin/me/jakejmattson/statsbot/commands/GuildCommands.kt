package me.jakejmattson.statsbot.commands

import me.aberrantfox.kjdautils.api.dsl.*
import me.aberrantfox.kjdautils.extensions.jda.fullName
import me.jakejmattson.statsbot.extensions.*
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Guild
import java.time.format.DateTimeFormatter

@CommandSet("Guild")
fun guildCommands() = commands {
    command("Overview") {
        execute {
            val guild = it.guild!!

            it.respondEmbed {
                title = guild.name
                description = guild.description
                thumbnail = guild.iconUrl

                with(guild) {
                    val invite = vanityUrl ?: retrieveInvites().complete().maxBy { it.uses }?.url ?: "<None>"

                    addField("Members", memberInfo())
                    addField("Channels", channelInfo())
                    addField("Emotes", emoteInfo())
                    addField("Other", otherInfo())
                    addField("Invite", invite)
                }
            }
        }
    }
}

private fun Guild.memberInfo(): String {
    val onlineMembers = members.filter { it.onlineStatus != OnlineStatus.OFFLINE }.size

    return wrapCode {
        appendln("Owner - ${owner?.fullName()}")
        appendln("Users - $onlineMembers online/${members.size}")
        appendln("Nitro - Tier ${boostTier.key} ($boostCount boosts)")
        appendln("Roles - ${roles.size}")
    }
}

private fun Guild.channelInfo() = wrapCode {
    appendln("Categories - ${categories.size}")
    appendln("Text       - ${textChannels.size}")
    appendln("Voice      - ${voiceChannels.size}")
}

private fun Guild.emoteInfo() = wrapCode {
    val staticEmotes = emotes.filter { !it.isAnimated }
    val animatedEmotes = emotes.filter { it.isAnimated }

    appendln("Static   - ${staticEmotes.size}/$maxEmotes")
    appendln("Animated - ${animatedEmotes.size}/$maxEmotes")
}

private fun Guild.otherInfo() = wrapCode {
    appendln("Region   - $region")
    appendln("Creation - ${timeCreated.format(DateTimeFormatter.RFC_1123_DATE_TIME)}")
}