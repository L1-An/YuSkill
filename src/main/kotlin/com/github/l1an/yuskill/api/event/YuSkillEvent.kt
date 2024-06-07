package com.github.l1an.yuskill.api.event

import taboolib.platform.type.BukkitProxyEvent

open class YuSkillEvent : BukkitProxyEvent() {

    /**
     * call 事件并返回它本身
     */
    inline fun <reified T : YuSkillEvent> callEvent(): T {
        call()
        return this as T
    }

    /**
     * call 事件并执行被取消回调
     */
    inline fun <reified T : YuSkillEvent> T.callEventBack(cancelled: T.() -> Unit = {}): T {
        call()
        if (isCancelled) {
            cancelled()
        }
        return this
    }

}