package me.jakejmattson.statsbot.preconditions

import me.aberrantfox.kjdautils.api.dsl.*
import me.aberrantfox.kjdautils.internal.command.*

@Precondition
fun produceIsGuildPrecondition() = precondition {
    if (it.guild != null)
        Pass
    else
        Fail()
}