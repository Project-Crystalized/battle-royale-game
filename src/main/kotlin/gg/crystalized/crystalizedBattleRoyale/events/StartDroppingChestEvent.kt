package gg.crystalized.crystalizedBattleRoyale.events

import gg.crystalized.crystalizedBattleRoyale.CrystalizedBattleRoyale
import gg.crystalized.crystalizedBattleRoyale.Game
import gg.crystalized.crystalizedBattleRoyale.data.DroppingChest
import gg.crystalized.crystalizedBattleRoyale.tasks.DropChestTask
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Entity
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack

class StartDroppingChestEvent(val game: Game, val loc: Location): Event() {

    companion object: Listener {
        private val HANDLERS = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList {
            return HANDLERS
        }

        @EventHandler
        fun dropChest(event: StartDroppingChestEvent){
            val armorStand: Entity = event.loc.world.spawn(event.loc, ArmorStand::class.java) { stand ->
                stand.isVisible = false
                stand.isMarker = true
                stand.setGravity(false)
                stand.isSmall = false
                stand.isInvulnerable = true
                val equipment = stand.equipment
                equipment.helmet = ItemStack(Material.CHEST)
            }

            val chest = DroppingChest(null, armorStand as ArmorStand)
            val task = DropChestTask(chest, event.game)
            chest.task = task.apply {
                runTaskTimer(CrystalizedBattleRoyale.instance, 1L, 1L)
            }

            event.game.droppingChests.add(chest)
        }

    }

    override fun getHandlers(): HandlerList {
        return HANDLERS
    }

}