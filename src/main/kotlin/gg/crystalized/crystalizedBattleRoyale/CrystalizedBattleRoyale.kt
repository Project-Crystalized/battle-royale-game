package gg.crystalized.crystalizedBattleRoyale

import gg.crystalized.crystalizedBattleRoyale.utils.FileUtils
import org.bukkit.plugin.java.JavaPlugin

class CrystalizedBattleRoyale : JavaPlugin() {

    init {
        instance = this
    }

    companion object{
        lateinit var instance: CrystalizedBattleRoyale
    }

    val messages = mutableMapOf<String, String>()
    var debug = false
    var game: Game? = null

    override fun onEnable() {
        // Plugin startup logic
        FileUtils.cacheMessages()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
