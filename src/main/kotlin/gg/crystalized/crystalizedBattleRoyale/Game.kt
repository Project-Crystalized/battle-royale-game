package gg.crystalized.crystalizedBattleRoyale

import gg.crystalized.crystalizedBattleRoyale.data.DataManager
import gg.crystalized.crystalizedBattleRoyale.data.DroppingChest
import gg.crystalized.crystalizedBattleRoyale.display.GameScoreboardManager
import gg.crystalized.crystalizedBattleRoyale.enums.GameState
import gg.crystalized.crystalizedBattleRoyale.tasks.*
import gg.crystalized.crystalizedBattleRoyale.utils.Utils
import gg.crystalized.crystalizedBattleRoyale.utils.VisualUtils
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import kotlin.random.Random

class Game {

    val config = CrystalizedBattleRoyale.instance.config

    val players = mutableSetOf<Player>()
    val entrance = Utils.getLocationFromFile("entrance")
    val entranceThreshold = config.getInt("threshold", 20)
    val warmupDelay = config.getInt("warmup-delay")
    var status = GameState.WAITING
    val minimumPlayers = config.getInt("minimum-players")
    val skipToStart = config.getInt("skip-to-start-with")
    var waitingTime = config.getInt("waiting-time")
    var timerTask = TimerTask(this).apply { runTaskTimer(CrystalizedBattleRoyale.instance, 20L, 20L) }

    var waitingTask: WaitingTask? = null
    var startingTask: StartingTask? = null
    var warmupTask: WarmupTask? = null
    val chests = DataManager.readChests().toMutableList()
    var curentTime = waitingTime
    val scoreboard = GameScoreboardManager(this)
    val droppingChests = mutableListOf<DroppingChest>()


    fun startWarmup(){
        // Must ensure that the teleport region is loaded to avoid server overload...
        val task = WarmupTask(players)
        warmupTask = task.apply {
            runTaskLater(CrystalizedBattleRoyale.instance,warmupDelay * 20L )
        }
    }

    fun giveItems(player: Player){
        player.inventory.chestplate = ItemStack(Material.ELYTRA)
    }

    fun teleportRandom(player: Player){

        //Gets the entrance location as a basis and randomly add to its components
        val entranceLoc = entrance.clone().add(
            Random.nextInt(entranceThreshold).toDouble(),
            Random.nextInt(entranceThreshold).toDouble(),
            Random.nextInt(entranceThreshold).toDouble()
        )

        player.teleport(entranceLoc)

    }

    fun winnerActions(){
        players.first().let{ winner ->
            VisualUtils.sendMessage(CrystalizedBattleRoyale.instance.messages["winner"].toString(), winner)

            //temporary...
            timerTask.cancel()
            CrystalizedBattleRoyale.instance.game = null
        }
    }

    fun hasGameStarted(): Boolean{
        return status == GameState.WARMUP || status == GameState.RUNNING1 || status == GameState.RUNNING2
    }

    fun canStart(): Boolean{
        return players.size >= minimumPlayers
    }
    fun endGame(): Boolean{
        return players.size == 1
    }




}