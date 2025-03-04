package gg.crystalized.crystalizedBattleRoyale.utils

import gg.crystalized.crystalizedBattleRoyale.CrystalizedBattleRoyale
import org.bukkit.Bukkit

object FileUtils {

    fun cacheMessages(){
        CrystalizedBattleRoyale.instance.let { instance ->
            Bukkit.getScheduler().runTaskAsynchronously(instance, Runnable {
                instance.reloadConfig()
                val config = instance.config
                val map = instance.messages
                config.getConfigurationSection("messages")?.getValues(false)?.forEach{ (key, value) ->
                    map[key] = value.toString()
                }
                if(instance.debug) instance.logger.info("Loaded all messages from config.yml")
            })

        }

    }

}