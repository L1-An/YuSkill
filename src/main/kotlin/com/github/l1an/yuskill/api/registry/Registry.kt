package com.github.l1an.yuskill.api.registry

import com.github.l1an.yuskill.api.annotation.YuSkill
import taboolib.common.LifeCycle
import taboolib.common.io.runningClasses
import taboolib.common.platform.Awake

/**
 * RaySkillSystem
 * top.maplex.rayskillsystem.utils.auto
 *
 * @author FxRayHugues
 */
@Suppress("unused")
object Registry {

    /**
     * 自动注册注解
     */
    @Awake(LifeCycle.ACTIVE)
    fun registerSkill() {
        runningClasses.forEach { clazz ->
            if (clazz.isAnnotationPresent(YuSkill::class.java)) {
                val function = clazz.getMethod("register")
                function.isAccessible = true

                val target = clazz.getDeclaredConstructor()
                target.isAccessible = true
                val instance = target.newInstance()
                function.invoke(instance)
            }
        }
    }

}