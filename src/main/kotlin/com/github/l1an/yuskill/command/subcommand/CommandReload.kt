package com.github.l1an.yuskill.command.subcommand

import org.bukkit.command.CommandSender
import org.serverct.parrot.parrotx.lang.LanguageType
import org.serverct.parrot.parrotx.lang.sendLang
import org.serverct.parrot.parrotx.mechanism.Reloadables
import taboolib.common.platform.command.subCommandExec

val CommandReload = subCommandExec<CommandSender> {
    Reloadables.execute()
    sender.sendLang("command-reload", type = LanguageType.Done)
}