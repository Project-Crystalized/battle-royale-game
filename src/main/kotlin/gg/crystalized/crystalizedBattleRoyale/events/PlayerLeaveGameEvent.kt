package gg.crystalized.crystalizedBattleRoyale.events

import gg.crystalized.crystalizedBattleRoyale.Game
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener

class PlayerLeaveGameEvent(val game: Game, val player: Player): Event() {

    companion object: Listener {

        private val HANDLERS = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList {
            return HANDLERS
        }

       @EventHandler
       fun playerLeaveEvent(event: PlayerLeaveGameEvent){

       }

    }

    override fun getHandlers(): HandlerList {
        return HANDLERS
    }
}