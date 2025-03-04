package gg.crystalized.crystalizedBattleRoyale.utils

import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.entity.Player

object VisualUtils {

    fun sendMessage(message: String, players: Set<Player>){
        val msg = MiniMessage.miniMessage().deserialize(message)
        players.forEach{
            it.sendMessage(msg)
        }
    }

    fun sendMessage(message: String, players: Player){
        val msg = MiniMessage.miniMessage().deserialize(message)
        players.sendMessage(msg)
    }

}