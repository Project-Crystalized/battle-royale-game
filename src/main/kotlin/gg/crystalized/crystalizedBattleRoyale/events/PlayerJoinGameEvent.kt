package gg.crystalized.crystalizedBattleRoyale.events

import gg.crystalized.crystalizedBattleRoyale.CrystalizedBattleRoyale
import gg.crystalized.crystalizedBattleRoyale.Game
import gg.crystalized.crystalizedBattleRoyale.enums.GameState
import gg.crystalized.crystalizedBattleRoyale.utils.VisualUtils
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener

class PlayerJoinGameEvent(val game: Game, val player: Player) : Event() {

    companion object: Listener {
        private val HANDLERS = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList = HANDLERS

        @EventHandler
        fun onPlayerJoinGame(event: PlayerJoinGameEvent) {

            val player = event.player
            val game = event.game

            game.players.add(player)
            VisualUtils.sendMessage(CrystalizedBattleRoyale.instance.messages["join-game"].toString(), player)

            if (game.status == GameState.WAITING && game.canStart()) {

                Bukkit.getPluginManager().callEvent(ChangeGameStatusEvent(game))

            }
        }

    }

    override fun getHandlers(): HandlerList = HANDLERS
}