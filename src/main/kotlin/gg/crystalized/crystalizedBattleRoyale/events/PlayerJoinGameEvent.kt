package gg.crystalized.crystalizedBattleRoyale.events

import gg.crystalized.crystalizedBattleRoyale.CrystalizedBattleRoyale
import gg.crystalized.crystalizedBattleRoyale.Game
import gg.crystalized.crystalizedBattleRoyale.enums.GameState
import gg.crystalized.crystalizedBattleRoyale.tasks.StartingTask
import gg.crystalized.crystalizedBattleRoyale.utils.VisualUtils
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener

class PlayerJoinGameEvent(val game: Game, val player: Player): Event() {

    companion object: Listener{

        private val HANDLERS = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList {
            return HANDLERS
        }

        @EventHandler
        fun joinGameListener(event: PlayerJoinGameEvent){
            event.player.let { p ->
                event.game.players.add(p)
                VisualUtils.sendMessage(CrystalizedBattleRoyale.instance.messages["join-game-message"].toString(), p)
            }
            event.game.let { game ->
                if(game.status == GameState.WAITING && game.canStart()){
                    //Start starting phase
                    game.waitingTask?.cancel()

                    VisualUtils.sendMessage(CrystalizedBattleRoyale.instance.messages["starting-phase-message"].toString(), game.players)
                    val task = StartingTask(game)
                    game.startingTask = task.apply {
                        runTaskLater(CrystalizedBattleRoyale.instance,
                        CrystalizedBattleRoyale.instance.config.getInt("starting-time") * 20L)
                    }
                }
            }

        }

    }

    override fun getHandlers(): HandlerList {
        return HANDLERS
    }

}