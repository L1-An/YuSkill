package com.github.l1an.yuskill.manager

import org.serverct.parrot.parrotx.mechanism.Reloadable
import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigNode
import taboolib.module.configuration.Configuration

@Suppress("unused")
object ConfigManager {

    @Config(autoReload = true)
    @Reloadable
    lateinit var config: Configuration
        private set

    /** 释放技能是否发送通知 */
    @ConfigNode("settings.cast-notify.enable")
    val isNotify: Boolean = true
    /** 释放技能通知半径 */
    @ConfigNode("settings.cast-notify.radius")
    val castNotifyRadius: Int = 20

}