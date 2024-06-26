package com.github.l1an.yuskill.module.team

import org.bukkit.entity.LivingEntity

interface AbstractTeam {

    val name: String

    fun register() {
        TeamManager.team = this
    }

    /**
     * true = 敌人
     * false = 队友
     */
    fun canAttack(damager: LivingEntity, entity: LivingEntity): Boolean

}