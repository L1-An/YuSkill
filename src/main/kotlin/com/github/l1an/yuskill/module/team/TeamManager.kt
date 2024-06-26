package com.github.l1an.yuskill.module.team

import com.github.l1an.yuskill.module.team.impl.TeamMinecraft
import org.bukkit.entity.LivingEntity

object TeamManager {

    var team: AbstractTeam = TeamMinecraft

    //true是可以攻击
    //false是不可以
    fun canAttack(damager: LivingEntity, entity: LivingEntity): Boolean {
        return team.canAttack(damager, entity)
    }

}