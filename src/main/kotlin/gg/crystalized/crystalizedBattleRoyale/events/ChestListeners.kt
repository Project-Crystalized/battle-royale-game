package gg.crystalized.crystalizedBattleRoyale.events

import gg.crystalized.crystalizedBattleRoyale.CrystalizedBattleRoyale
import gg.crystalized.crystalizedBattleRoyale.data.toBlockLocationData
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.ItemFactory
import org.bukkit.inventory.ItemStack
import kotlin.random.Random

class ChestListeners: Listener {

    @EventHandler
    fun closeChest(event: InventoryCloseEvent){
        if(event.inventory.type == InventoryType.CHEST) {
            val contents = event.inventory.contents.filter { it?.type != Material.AIR }
            event.inventory.clear()
            val startLoc = event.inventory.location?.clone()?.add(0.0, 1.0, 0.0)
            event.inventory.location?.block?.type = Material.AIR

            contents.forEach {
                if (it != null) {
                    startLoc?.add(
                        Random.nextDouble(1.0),
                        0.0,
                        Random.nextDouble(1.0)
                    )?.let { it1 -> startLoc.world?.dropItem(it1, it) }
                }

            }
        }
    }

    @EventHandler
    fun generateLoot(event: InventoryOpenEvent){
        if(event.inventory.type == InventoryType.CHEST){
            val game = CrystalizedBattleRoyale.instance.game
            //Handle loot generation
            val loc = event.inventory.location?.toBlockLocation()
            game?.chests?.find { it.position == loc?.toBlockLocationData()}?.let { tierChest ->
                when(tierChest.tier){
                    //just for testing rn
                    1 -> {
                        event.inventory.addItem(ItemStack.of(Material.APPLE))
                    }
                    2 -> {
                        event.inventory.addItem(ItemStack.of(Material.GOLDEN_APPLE))
                    }
                    3 -> {
                        event.inventory.addItem(ItemStack.of(Material.IRON_INGOT))
                    }
                    4 -> {
                        event.inventory.addItem(ItemStack.of(Material.EMERALD))
                    }
                }

                game.chests.remove(tierChest)
            }
        }
    }

}