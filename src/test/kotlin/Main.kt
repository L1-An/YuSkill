fun main() {
    val ms: Long = 1500 // 1.5s -> 30tick
    println(ms / 1000)
    println(ms / 1000 * 20)
    println(ms.msToTick)
}

/**
 * 将 ms 转换为 tick
 */
fun msToTick(ms: Long): Int = (ms / 1000 * 20).toInt()
val Long.msToTick
    get() = msToTick(this)