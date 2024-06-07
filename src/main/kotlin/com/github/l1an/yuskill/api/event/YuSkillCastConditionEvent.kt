package com.github.l1an.yuskill.api.event

import com.github.l1an.yuskill.module.skill.Skill
import org.bukkit.entity.LivingEntity

/**
 * 技能条件检测事件
 * @param isCondition 条件是否通过
 */
class YuSkillCastConditionEvent(
    val caster: LivingEntity,
    val skill: Skill,
    val level: Int,
    var isCondition: Boolean
) : YuSkillEvent()