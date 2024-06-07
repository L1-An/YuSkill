package com.github.l1an.yuskill.utils.cooldown

import org.bukkit.entity.LivingEntity
import java.util.concurrent.ConcurrentHashMap

/**
 * RaySkillSystem
 * top.maplex.rayskillsystem.utils.cooldown
 *
 * @author FxRayHughes
 */
object CooldownAPI {

    val map = ConcurrentHashMap<String, Long>()

    /**获取剩余时间，单位为 s */
    fun getTime(livingEntity: LivingEntity, key: String): Int {
        val mapKey = "${livingEntity.uniqueId}__${key}"
        val get = map.getOrDefault(mapKey, 0L)
        if (get <= 0) {
            return 0
        }
        val nowTime = System.currentTimeMillis()
        return ((get - nowTime) / 1000).toInt()
    }

    /** 获取剩余时间，单位为 ms */
    fun getTimeLong(livingEntity: LivingEntity, key: String): Long {
        val mapKey = "${livingEntity.uniqueId}__${key}"
        val get = map.getOrDefault(mapKey, 0L)
        if (get <= 0) {
            return 0
        }
        val nowTime = System.currentTimeMillis()
        return get - nowTime
    }

    /**
     * 检查是否可以执行
     * @return true可以执行，false不可执行
     */
    fun check(livingEntity: LivingEntity, key: String, tick: Long): Boolean {
        return check(livingEntity, key)
    }

    /**
     * 检查是否可以执行
     * @return true可以执行，false不可执行
     */
    fun check(livingEntity: LivingEntity, key: String): Boolean {
        val mapKey = "${livingEntity.uniqueId}__${key}"
        val get = map.getOrDefault(mapKey, -1L)
        val nowTime = System.currentTimeMillis()
        return nowTime <= get
    }

    /**
     * 设置冷却时间
     * @param tick tick
     */
    fun set(livingEntity: LivingEntity, key: String, tick: Long): Boolean {
        val mapKey = "${livingEntity.uniqueId}__${key}"
        val nowTime = System.currentTimeMillis()
        map[mapKey] = nowTime + (tick * 1000 / 20)
        return true
    }

}
