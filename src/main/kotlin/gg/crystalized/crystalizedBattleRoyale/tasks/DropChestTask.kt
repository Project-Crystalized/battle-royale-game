package gg.crystalized.crystalizedBattleRoyale.tasks

import gg.crystalized.crystalizedBattleRoyale.Game
import gg.crystalized.crystalizedBattleRoyale.data.DroppingChest
import gg.crystalized.crystalizedBattleRoyale.events.SpawnChestEvent
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.scheduler.BukkitRunnable

class DropChestTask(val chest: DroppingChest, val game: Game): BukkitRunnable() {

    override fun run() {
        val curLoc = chest.armorStand.location.clone()
        val newLoc = Location(
            curLoc.world,
            curLoc.x,
            curLoc.y - 0.1,
            curLoc.z
        )
        chest.armorStand.teleport(newLoc)

        //Checking if the chest has reached a block
        if(chest.armorStand.location.block.type != Material.AIR){
            //Spawn a chest here and remove the armorstand
            Bukkit.getPluginManager().callEvent(SpawnChestEvent(chest, game))
            chest.destroy(game)
        }
    }

}