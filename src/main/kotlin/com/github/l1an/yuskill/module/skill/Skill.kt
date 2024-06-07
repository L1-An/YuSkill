package com.github.l1an.yuskill.module.skill

import com.github.l1an.yuskill.YuSkill.PREFIX
import com.github.l1an.yuskill.manager.SkillManager
import org.bukkit.entity.LivingEntity
import org.bukkit.inventory.ItemStack
import org.serverct.parrot.parrotx.function.sendInfo
import taboolib.library.xseries.XMaterial
import taboolib.platform.util.buildItem

interface Skill {

    /** 技能名称, 注册在 SkillManager 的名称 */
    val name: String

    /** 技能类型 */
    val type: String

    /** 返回技能冷却时间，单位 tick */
    fun getCooldown(caster: LivingEntity, level: Int): Long {
        return 0
    }

    /** 返回技能图标 */
    fun getIcon(caster: LivingEntity, level: Int): ItemStack {
        return buildItem(XMaterial.PAPER) {
            this.name = "&f${this@Skill.name}"
            colored()
        }
    }

    /** 施法条件 */
    fun condition(caster: LivingEntity, level: Int): Boolean = true

    /** 释放技能前执行 */
    fun pre(caster: LivingEntity, level: Int): Boolean = true

    /** 技能主体 */
    fun run(caster: LivingEntity, level: Int): Boolean = true

    /** 释放技能后执行 */
    fun end(caster: LivingEntity, level: Int): Boolean = true

    /** 注册技能到 SkillManager */
    fun register() {
        SkillManager.skills[name] = this
        sendInfo("$PREFIX &a成功注册技能: &f $name")
    }

}