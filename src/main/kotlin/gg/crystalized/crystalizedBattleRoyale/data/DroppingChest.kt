package gg.crystalized.crystalizedBattleRoyale.data

import gg.crystalized.crystalizedBattleRoyale.Game
import gg.crystalized.crystalizedBattleRoyale.tasks.DropChestTask
import org.bukkit.entity.ArmorStand

data class DroppingChest(
    var task: DropChestTask?,
    val armorStand: ArmorStand
){

    fun destroy(game: Game){
        this.task?.cancel()
        this.armorStand.remove()
        game.droppingChests.remove(this)
    }

}