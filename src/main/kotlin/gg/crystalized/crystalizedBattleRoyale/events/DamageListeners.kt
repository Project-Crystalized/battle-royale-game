package gg.crystalized.crystalizedBattleRoyale.events

import gg.crystalized.crystalizedBattleRoyale.CrystalizedBattleRoyale
import gg.crystalized.crystalizedBattleRoyale.enums.GameState
import gg.crystalized.crystalizedBattleRoyale.utils.VisualUtils
import org.bukkit.Bukkit
import org.bukkit.entity.Arrow
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.entity.SpectralArrow
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent

class DamageListeners: Listener {

    @EventHandler
    fun damageEvent(event: EntityDamageEvent){
        CrystalizedBattleRoyale.instance.game?.let { game ->
            if(game.hasGameStarted()){
                if(event is EntityDamageByEntityEvent){

                    val damager = if (event.damager.type == EntityType.ARROW) {
                        if ((event.damager as Arrow).shooter is Player) {
                            (event.damager as Arrow).shooter as Player
                        } else null
                    } else if (event.damager.type == EntityType.SPECTRAL_ARROW) {
                        if ((event.damager as SpectralArrow).shooter is Player) {
                            (event.damager as SpectralArrow).shooter as Player
                        } else null
                    } else if (event.damager.type == EntityType.PLAYER) {
                        event.damager as Player
                    } else null

                    val victim = event.entity
                    if(victim is Player){
                        if(victim.health - event.finalDamage <= 0){
                            when(damager){
                                is Player -> {
                                    VisualUtils.sendMessage(
                                        CrystalizedBattleRoyale.instance.messages["killed-by-player"].toString(),
                                        game.players,
                                        setOf(Pair("%killer%", damager.name), Pair("%victim%", victim.name))
                                    )
                                }
                                else -> {
                                    VisualUtils.sendMessage(
                                        CrystalizedBattleRoyale.instance.messages["killed-unknown-reason"].toString(),
                                        game.players,
                                        setOf(Pair("%victim%", victim.name))
                                    )
                                }

                            }
                            Bukkit.getPluginManager().callEvent(EliminatePlayerEvent(game, victim))
                        }
                    }
                } else {
                    event.isCancelled = true
                }
            } else {
                event.isCancelled = true
            }
        }
    }

}