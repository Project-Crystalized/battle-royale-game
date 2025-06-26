package gg.crystalized.crystalizedBattleRoyale.events

import gg.crystalized.crystalizedBattleRoyale.CrystalizedBattleRoyale
import gg.crystalized.crystalizedBattleRoyale.Game
import gg.crystalized.crystalizedBattleRoyale.enums.GameState
import gg.crystalized.crystalizedBattleRoyale.tasks.StartingTask
import gg.crystalized.crystalizedBattleRoyale.utils.VisualUtils
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener

class ChangeGameStatusEvent(val game: Game): Event() {

    companion object: Listener {
        private val HANDLERS = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList {
            return HANDLERS
        }

        @EventHandler
        fun changeStatus(event: ChangeGameStatusEvent){
            event.game.let { game ->

                //The when statement will check the previous game status
                when(game.status){
                    GameState.WAITING -> {
                        game.status = GameState.STARTING

                        game.waitingTask?.cancel()

                        VisualUtils.sendMessage(
                            CrystalizedBattleRoyale.instance.messages["starting-phase"].toString(),
                            game.players
                        )

                        val task = StartingTask(game).apply {
                            runTaskLater(
                                CrystalizedBattleRoyale.instance,
                                CrystalizedBattleRoyale.instance.config.getInt("starting-time") * 20L
                            )
                        }
                        game.curentTime = CrystalizedBattleRoyale.instance.config.getInt("starting-time")
                        game.startingTask = task

                    }
                    GameState.STARTING -> {
                        game.status = GameState.WARMUP
                        game.curentTime = CrystalizedBattleRoyale.instance.config.getInt("warmup-delay")

                    }
                    GameState.WARMUP -> {
                        game.curentTime = CrystalizedBattleRoyale.instance.config.getInt("phase1-delay")
                    }
                    GameState.RUNNING1 -> TODO()
                    GameState.RUNNING2 -> TODO()
                    GameState.ENDING -> TODO()
                }
            }
        }

    }

    override fun getHandlers(): HandlerList {
        return HANDLERS
    }

}