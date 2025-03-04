package gg.crystalized.crystalizedBattleRoyale.tasks

import gg.crystalized.crystalizedBattleRoyale.Game
import gg.crystalized.crystalizedBattleRoyale.events.StartGameEvent
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable

class StartingTask(private val game: Game): BukkitRunnable() {
    override fun run() {

        Bukkit.getPluginManager().callEvent(StartGameEvent(game))
    }
}