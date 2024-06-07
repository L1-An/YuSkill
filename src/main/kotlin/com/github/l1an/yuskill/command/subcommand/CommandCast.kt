package com.github.l1an.yuskill.command.subcommand

import com.github.l1an.yuskill.manager.SkillManager
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import taboolib.common.platform.command.int
import taboolib.common.platform.command.player
import taboolib.common.platform.command.subCommand
import taboolib.common5.cint

/**
 * skill cast <skill> (level:1) (player)
 */
val CommandCast = subCommand {

    dynamic("skill") {
        suggestion<CommandSender> { _, _ ->
            SkillManager.skills.keys.toList()
        }

        execute<Player> { sender, ctx, _ ->
            SkillManager.eval(sender, ctx["skill"], 1)
        }

        int("level") {
            execute<Player> { sender, ctx, _ ->
                SkillManager.eval(sender, ctx["skill"], ctx["level"].cint)
            }

            player {
                execute<CommandSender> { _, ctx, _ ->
                    val player = Bukkit.getPlayerExact(ctx["player"])!!
                    SkillManager.eval(player, ctx["skill"], ctx["level"].cint)
                }
            }
        }
    }

}