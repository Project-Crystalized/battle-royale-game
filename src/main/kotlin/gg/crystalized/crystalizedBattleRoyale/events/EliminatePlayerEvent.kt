package gg.crystalized.crystalizedBattleRoyale.events

import gg.crystalized.crystalizedBattleRoyale.Game
import gg.crystalized.crystalizedBattleRoyale.enums.GameState
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener

class EliminatePlayerEvent(val game: Game, val player: Player): Event() {

    companion object: Listener {
        private val HANDLERS = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList {
            return HANDLERS
        }

        @EventHandler
        fun eliminatePlayer(event: EliminatePlayerEvent){

            when(event.game.status){

                GameState.WAITING -> TODO()
                GameState.STARTING -> TODO()
                GameState.ENDING -> TODO()
                else -> {
                    //Player died
                    handlePlayerDeath(event.player, event.game)
                }

            }
        }

        private fun handlePlayerDeath(player: Player, game: Game){
            game.players.remove(player)
            if(game.endGame()){
                //Enter Finishing Phase
                game.winnerActions()
            }
        }

    }


    override fun getHandlers(): HandlerList {
        return HANDLERS
    }
}