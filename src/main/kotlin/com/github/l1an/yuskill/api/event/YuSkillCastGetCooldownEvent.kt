package com.github.l1an.yuskill.api.event

import com.github.l1an.yuskill.module.skill.Skill
import org.bukkit.entity.LivingEntity

/**
 * 技能冷却事件
 * @param cooldown 冷却时间
 * @param isCooldown 是否正在冷却
 */
class YuSkillCastGetCooldownEvent(
    val caster: LivingEntity,
    val skill: Skill,
    val level: Int,
    var cooldown: Long,
    var isCooldown: Boolean
) : YuSkillEvent()