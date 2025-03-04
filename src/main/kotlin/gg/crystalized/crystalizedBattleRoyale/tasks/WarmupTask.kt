package gg.crystalized.crystalizedBattleRoyale.tasks

import gg.crystalized.crystalizedBattleRoyale.CrystalizedBattleRoyale
import gg.crystalized.crystalizedBattleRoyale.utils.VisualUtils
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class WarmupTask(private val players: Set<Player>): BukkitRunnable() {
    override fun run() {
        VisualUtils.sendMessage(CrystalizedBattleRoyale.instance.messages["warmup-end-message"].toString(), players)
    }

}