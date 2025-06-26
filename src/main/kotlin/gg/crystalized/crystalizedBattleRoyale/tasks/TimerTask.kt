package gg.crystalized.crystalizedBattleRoyale.tasks

import gg.crystalized.crystalizedBattleRoyale.Game
import org.bukkit.scheduler.BukkitRunnable

class TimerTask(val game: Game): BukkitRunnable() {
    override fun run() {
        game.curentTime --
        game.scoreboard.updateTime()
    }
}