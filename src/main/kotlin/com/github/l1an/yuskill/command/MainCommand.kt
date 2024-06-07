package com.github.l1an.yuskill.command

import com.github.l1an.yuskill.command.subcommand.CommandCast
import com.github.l1an.yuskill.command.subcommand.CommandReload
import org.serverct.parrot.parrotx.feature.Debug.CommandDebug
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.mainCommand
import taboolib.expansion.createHelper

@CommandHeader(
    name = "yuskill",
    aliases = ["yus", "ys", "skill"],
    permission = "yuskill.command.use"
)
object MainCommand {

    @CommandBody
    val main = mainCommand {
        createHelper()
    }

    @CommandBody
    val reload = CommandReload

    @CommandBody
    val debug = CommandDebug

    @CommandBody
    val cast = CommandCast

}