package com.github.l1an.yuskill.module.team.impl

import com.github.l1an.yuskill.module.team.AbstractTeam
import org.bukkit.Bukkit
import org.bukkit.entity.LivingEntity

object TeamMinecraft : AbstractTeam {

    override val name: String = "原版队伍系统"

    private val api by lazy {
        Bukkit.getScoreboardManager()?.mainScoreboard
    }

    override fun canAttack(damager: LivingEntity, entity: LivingEntity): Boolean {
        if (damager == entity) {
            return false
        }
        val from = api?.getEntryTeam(damager.name) ?: return true
        val to = api?.getEntryTeam(entity.name) ?: return true
        return from != to
    }

}
