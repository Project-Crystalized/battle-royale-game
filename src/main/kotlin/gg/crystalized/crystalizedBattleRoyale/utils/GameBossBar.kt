package gg.crystalized.crystalizedBattleRoyale.utils

import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage


class GameBossBar {

    var currentMessage = ""
    var color = BossBar.Color.YELLOW

    var bossBar = BossBar.bossBar(
        Component.text(),
        1.0F,
        color,
        BossBar.Overlay.PROGRESS
    )

    fun updateName(){
        bossBar.name(MiniMessage.miniMessage().deserialize(currentMessage))
    }


}