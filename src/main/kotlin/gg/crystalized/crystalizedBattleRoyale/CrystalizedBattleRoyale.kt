package gg.crystalized.crystalizedBattleRoyale

import gg.crystalized.crystalizedBattleRoyale.commands.AdminCommands
import gg.crystalized.crystalizedBattleRoyale.events.*
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

        saveDefaultConfig()
        // Plugin startup logic
        FileUtils.cacheMessages()

        server.pluginManager.registerEvents(ChangeGameStatusEvent, this)
        server.pluginManager.registerEvents(ChestListeners(), this)
        server.pluginManager.registerEvents(DamageListeners(), this)
        server.pluginManager.registerEvents(EliminatePlayerEvent, this)
        server.pluginManager.registerEvents(PlayerJoinGameEvent, this)
        server.pluginManager.registerEvents(StartGameEvent, this)
        server.pluginManager.registerEvents(SpawnChestEvent, this)
        server.pluginManager.registerEvents(StartDroppingChestEvent, this)




        getCommand("test")?.setExecutor(AdminCommands())
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
