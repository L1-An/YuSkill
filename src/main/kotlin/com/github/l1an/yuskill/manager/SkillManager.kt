package com.github.l1an.yuskill.manager

import com.github.l1an.yuskill.api.event.*
import com.github.l1an.yuskill.manager.ConfigManager.castNotifyRadius
import com.github.l1an.yuskill.manager.ConfigManager.isNotify
import com.github.l1an.yuskill.module.selector.Area
import com.github.l1an.yuskill.module.skill.Skill
import com.github.l1an.yuskill.utils.TimeUtils.tickToSecond
import com.github.l1an.yuskill.utils.cooldown.CooldownAPI
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.serverct.parrot.parrotx.lang.LanguageType
import org.serverct.parrot.parrotx.lang.sendLang

object SkillManager {

    val skills = HashMap<String, Skill>()

    fun getSkill(name: String): Skill? {
        return skills[name]
    }

    fun eval(caster: LivingEntity, name: String, level: Int): Boolean {
        val skill = getSkill(name) ?: return false
        // 检测冷却时间
        if (skill.getCooldown(caster, level) > 0) {
            val isInCd = CooldownAPI.check(caster, "Skill__$name")
            val cd = skill.getCooldown(caster, level)
            YuSkillCastGetCooldownEvent(caster, skill, level, cd, isInCd)
                .callEvent<YuSkillCastGetCooldownEvent>()
                .let { e ->
                    if (e.isCooldown) {
                        caster.sendLang(
                            "skill-cooldown",
                            skill.name to "skill",
                            e.cooldown.tickToSecond to "cooldown",
                            type = LanguageType.Error
                        )
                        return false
                    }
                }
        }

        // 检测条件是否通过
        if (!skill.condition(caster, level).run original@{
                YuSkillCastConditionEvent(caster, skill, level, this)
                    .callEvent<YuSkillCastConditionEvent>()
                    .isCondition
            }) {
            return false
        }

        // 检测 pre 是否通过
        if (!skill.pre(caster, level).run {
                YuSkillCastPreEvent(caster, skill, level, this)
                    .callEvent<YuSkillCastPreEvent>()
                    .canRun
            }) {
            return false
        }

        // 检测 run 是否通过
        if (!skill.run(caster, level).run {
                YuSkillCastRunEvent(caster, skill, level, this)
                    .callEvent<YuSkillCastRunEvent>()
                    .canRun
            }) {
            return false
        }

        // 发送释放技能通知
        if (isNotify) {
            Area.get(caster, castNotifyRadius.toDouble()).forEach {
                if (it is Player) {
                    it.sendLang("other-cast-skill", it.name to "caster", skill.name to "skill", type = LanguageType.Info)
                }
            }
        }

        // 检测是否完成 end 阶段的代码
        if (!skill.end(caster, level).run {
                YuSkillCastEndEvent(caster, skill, level, this)
                    .callEvent<YuSkillCastEndEvent>()
                    .canRun
            }) {
            return false
        }

        // 进入冷却
        if (skill.getCooldown(caster, level) > 0) {
            val cooldown = skill.getCooldown(caster, level)
            CooldownAPI.set(caster, "Skill__$name", cooldown)
        }
        return true
    }

}