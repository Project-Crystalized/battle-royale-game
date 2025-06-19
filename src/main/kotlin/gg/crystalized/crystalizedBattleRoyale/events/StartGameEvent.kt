package gg.crystalized.crystalizedBattleRoyale.events

import gg.crystalized.crystalizedBattleRoyale.CrystalizedBattleRoyale
import gg.crystalized.crystalizedBattleRoyale.Game
import gg.crystalized.crystalizedBattleRoyale.enums.GameState
import gg.crystalized.crystalizedBattleRoyale.utils.VisualUtils
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener

class StartGameEvent(val game: Game): Event() {

    companion object: Listener {

        private val HANDLERS = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList {
            return HANDLERS
        }

        @EventHandler
        fun startGame(event: StartGameEvent){
            event.game.let { game ->
                game.players.forEach{ p ->
                    game.giveItems(p)
                    game.teleportRandom(p)
                }
                game.startWarmup()
                VisualUtils.sendMessage(CrystalizedBattleRoyale.instance.messages["grace-period"].toString(), game.players)
            }

        }

    }

    override fun getHandlers(): HandlerList {
        return HANDLERS
    }
}