package com.github.l1an.yuskill.api.event

import com.github.l1an.yuskill.module.skill.Skill
import org.bukkit.entity.LivingEntity

/**
 * 技能执行事件
 * @param canRun 是否执行通过
 */
class YuSkillCastRunEvent(
    val caster: LivingEntity,
    val skill: Skill,
    val level: Int,
    var canRun: Boolean
) : YuSkillEvent()