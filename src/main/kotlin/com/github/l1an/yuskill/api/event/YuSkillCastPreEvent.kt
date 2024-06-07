package com.github.l1an.yuskill.api.event

import com.github.l1an.yuskill.module.skill.Skill
import org.bukkit.entity.LivingEntity

/**
 * 技能执行前执行内容
 * @param canRun 是否能够正式执行
 */
class YuSkillCastPreEvent(
    val caster: LivingEntity,
    val skill: Skill,
    val level: Int,
    var canRun: Boolean
) : YuSkillEvent()