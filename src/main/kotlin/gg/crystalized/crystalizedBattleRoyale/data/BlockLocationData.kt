package gg.crystalized.crystalizedBattleRoyale.data

import org.bukkit.Location

data class BlockLocationData(
    val x: Int,
    val y: Int,
    val z: Int
)

fun Location.toBlockLocationData(): BlockLocationData{
    return BlockLocationData(this.blockX, this.blockY,this.blockZ)
}