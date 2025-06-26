package gg.crystalized.crystalizedBattleRoyale.commands

import gg.crystalized.crystalizedBattleRoyale.CrystalizedBattleRoyale
import gg.crystalized.crystalizedBattleRoyale.Game
import gg.crystalized.crystalizedBattleRoyale.events.PlayerJoinGameEvent
import gg.crystalized.crystalizedBattleRoyale.events.StartDroppingChestEvent
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class AdminCommands: CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, str: String, args: Array<out String>?): Boolean {

        if(sender is Player) {
            args?.let {
                when (it[0]) {
                    "start" -> {
                        CrystalizedBattleRoyale.instance.game = Game()

                        println("Chests are: ${CrystalizedBattleRoyale.instance.game?.chests}")
                    }

                    "join" -> {
                        CrystalizedBattleRoyale.instance.game?.let { game ->
                            Bukkit.getPluginManager().callEvent(PlayerJoinGameEvent(game, sender))
                        }

                    }
                    "dropchest" -> {
                        CrystalizedBattleRoyale.instance.game?.let { game ->
                            Bukkit.getPluginManager().callEvent(StartDroppingChestEvent(game, sender.location))
                        }
                    }

                    else -> {}
                }
            }
        }

        return true
    }
}