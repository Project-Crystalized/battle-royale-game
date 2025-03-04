package gg.crystalized.crystalizedBattleRoyale

import gg.crystalized.crystalizedBattleRoyale.enums.GameState
import gg.crystalized.crystalizedBattleRoyale.tasks.StartingTask
import gg.crystalized.crystalizedBattleRoyale.tasks.WaitingTask
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class Game {

    val players = mutableSetOf<Player>()
    val teleportLocations = mutableSetOf<Location>()
    var status = GameState.WAITING
    val minimumPlayers = CrystalizedBattleRoyale.instance.config.getInt("minimum-players")
    val skipToStart = CrystalizedBattleRoyale.instance.config.getInt("skip-to-start-with")
    var waitingTime = CrystalizedBattleRoyale.instance.config.getInt("waiting-time")

    var waitingTask: WaitingTask? = null
    var startingTask: StartingTask? = null

    fun startWarmup(){
        // First ensure that the teleport region is loaded to avoid server overload


        //Teleport players to random placed on the sky
        //Give an elytra for each one

    }

    fun giveItems(player: Player){
        player.inventory.chestplate = ItemStack(Material.ELYTRA)
    }

    fun teleportRandom(player: Player){
        teleportLocations.random().let { loc ->
            teleportLocations.remove(loc)
            player.teleport(loc)
        }

    }

    fun canStart(): Boolean{
        return players.size >= minimumPlayers
    }

}