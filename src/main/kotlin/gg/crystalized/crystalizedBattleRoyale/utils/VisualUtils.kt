package gg.crystalized.crystalizedBattleRoyale.utils

import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.entity.Player

object VisualUtils {

    fun sendMessage(message: String, players: Set<Player>, placeholders: Set<Pair<String, String>> = emptySet()){
        var tempMessage = message

        if(placeholders.isNotEmpty()){
            placeholders.forEach {
                tempMessage = tempMessage.replace(it.first, it.second)
            }
        }
       val finalMessage = MiniMessage.miniMessage().deserialize(tempMessage)

        players.forEach{
            it.sendMessage(finalMessage)
        }
    }

    fun sendMessage(message: String, players: Player, placeholders: Set<Pair<String, String>> = emptySet()){
        var tempMessage = message
        if(placeholders.isNotEmpty()){
            placeholders.forEach {
                tempMessage = tempMessage.replace(it.first, it.second)
            }
        }
        val finalMessage = MiniMessage.miniMessage().deserialize(tempMessage)

        players.sendMessage(finalMessage)
    }

}