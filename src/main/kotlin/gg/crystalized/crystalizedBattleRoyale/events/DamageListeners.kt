package gg.crystalized.crystalizedBattleRoyale.events

import gg.crystalized.crystalizedBattleRoyale.CrystalizedBattleRoyale
import gg.crystalized.crystalizedBattleRoyale.enums.GameState
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent

class DamageListeners: Listener {

    @EventHandler
    fun damageEvent(event: EntityDamageEvent){
        CrystalizedBattleRoyale.instance.game?.let { game ->
            if(game.status == GameState.RUNNING1 || game.status == GameState.RUNNING2){
                if(event is EntityDamageByEntityEvent){
                    val damager = event.damager
                    val victim = event.entity
                    if(victim is Player){
                        if(victim.health - event.finalDamage <= 0){
                            when(damager){
                                is Player -> {

                                }
                                else -> {

                                }
                            }
                        }
                    }
                }
            } else {
                event.isCancelled = true
            }
        }
    }

}