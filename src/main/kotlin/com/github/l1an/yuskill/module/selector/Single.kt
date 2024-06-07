package com.github.l1an.yuskill.module.selector

import com.github.l1an.yuskill.api.annotation.YuSelector
import org.bukkit.entity.LivingEntity

/**
 * RaySkillSystem
 * top.maplex.rayskillsystem.skill.tools.target
 *
 * @author FxRayHugues
 */
@YuSelector
object Single {

    /**
     * 看到的第一个实体
     * @param source 触发者
     * @param range 范围
     * @param tolerance 容错率
     * @param filter 过滤回调
     */
    fun get(
        source: LivingEntity,
        range: Double,
        tolerance: Double,
        filter: LivingEntity.() -> Boolean = { true },
    ): LivingEntity? {
        val world = source.world
        world.rayTraceEntities(
            source.eyeLocation,
            source.eyeLocation.direction, range, tolerance
        ) { entity ->
            entity is LivingEntity && filter.invoke(entity)
        }?.let {
            it.hitEntity?.let { entity ->
                return entity as LivingEntity
            }
        }
        return null
    }

}