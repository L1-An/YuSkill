package com.github.l1an.yuskill

import org.serverct.parrot.parrotx.function.sendInfo
import taboolib.common.platform.Platform
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.pluginVersion
import taboolib.module.metrics.Metrics

object YuSkill : Plugin() {

    const val PREFIX = "&f[ &bYu&3Skill &f]"

    override fun onEnable() {
        Metrics(22231, pluginVersion, Platform.BUKKIT)

        sendInfo {
            +"$PREFIX &aYuSkill has been loaded! - $pluginVersion"
            +"$PREFIX &bAuthor by L1An(Discord: &el1_an.&b)"
        }
    }

}