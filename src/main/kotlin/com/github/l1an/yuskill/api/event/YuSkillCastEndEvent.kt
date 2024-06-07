package com.github.l1an.yuskill.api.event

import com.github.l1an.yuskill.module.skill.Skill
import org.bukkit.entity.LivingEntity

/**
 * 技能结束事件
 * @param canRun 是否通过
 */
class YuSkillCastEndEvent(
    val caster: LivingEntity,
    val skill: Skill,
    val level: Int,
    var canRun: Boolean
) : YuSkillEvent()