package gg.crystalized.crystalizedBattleRoyale.events

import gg.crystalized.crystalizedBattleRoyale.Game
import gg.crystalized.crystalizedBattleRoyale.data.DroppingChest
import gg.crystalized.crystalizedBattleRoyale.data.TierChest
import gg.crystalized.crystalizedBattleRoyale.data.toBlockLocationData
import org.bukkit.Material
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener

class SpawnChestEvent(val chest: DroppingChest, val game: Game): Event() {

    companion object: Listener {
        private val HANDLERS = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList {
            return HANDLERS
        }

        @EventHandler
        fun spawnChest(event: SpawnChestEvent){
            val chest = event.chest
            val newLoc = chest.armorStand.location.clone().add(0.0,1.0,0.0)
            newLoc.block.type = Material.CHEST
            event.game.chests.add(TierChest(4, newLoc.toBlockLocationData()))
        }

    }

    override fun getHandlers(): HandlerList {
        return HANDLERS
    }

}