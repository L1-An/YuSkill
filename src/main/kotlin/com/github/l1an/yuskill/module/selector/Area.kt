package com.github.l1an.yuskill.module.selector

import com.github.l1an.yuskill.api.annotation.YuSelector
import org.bukkit.Location
import org.bukkit.entity.LivingEntity

/**
 * RaySkillSystem
 * top.maplex.rayskillsystem.skill.tools.target
 *
 * @author FxRayHugues
 */
@YuSelector
object Area {

    /**
     * 获取圆形范围内的实体，默认不包含自己
     * @param source 触发者
     * @param range 范围
     * @param self 是否包含自己
     */
    fun get(source: LivingEntity, range: Double, self: Boolean = false): List<LivingEntity> {
        return source.getNearbyEntities(range, range, range).mapNotNull {
            if (!it.isDead) it as? LivingEntity
            else null
        }.toMutableList().apply {
            if (self) {
                add(source)
            }
        }
    }

    fun get(source: Location, range: Double): List<LivingEntity> {
        source.world?.getNearbyEntities(source, range, range, range)?.mapNotNull {
            if (!it.isDead) it as? LivingEntity
            else null
        }?.toMutableList()?.let {
            return it
        }
        return emptyList()
    }

}