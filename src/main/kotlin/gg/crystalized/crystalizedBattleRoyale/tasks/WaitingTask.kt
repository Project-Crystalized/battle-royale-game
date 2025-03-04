package gg.crystalized.crystalizedBattleRoyale.tasks

import gg.crystalized.crystalizedBattleRoyale.CrystalizedBattleRoyale
import gg.crystalized.crystalizedBattleRoyale.Game
import gg.crystalized.crystalizedBattleRoyale.utils.PlaceholderUtils
import org.bukkit.scheduler.BukkitRunnable

class WaitingTask(val game: Game): BukkitRunnable() {
    override fun run() {

        val msg = PlaceholderUtils.replacePlaceholder(
                PlaceholderUtils.replacePlaceholder(
                CrystalizedBattleRoyale.instance.messages["bossbar-waiting-message"].toString(),
                "%time%",
                game.waitingTime.toString()),
            "%remaining_players%",
            (game.players.size - game.minimumPlayers).toString()
        )

    }
}