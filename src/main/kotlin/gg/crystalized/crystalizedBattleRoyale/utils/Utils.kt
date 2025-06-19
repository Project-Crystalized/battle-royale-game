package gg.crystalized.crystalizedBattleRoyale.utils

import gg.crystalized.crystalizedBattleRoyale.CrystalizedBattleRoyale
import org.bukkit.Bukkit
import org.bukkit.Location

object Utils {

    fun getLocationFromFile(location: String): Location {
        val file = CrystalizedBattleRoyale.instance.config
        return Location(
            Bukkit.getWorld(file.getString("${location}.world").toString()),
            file.getString("${location}.x")?.toDouble() ?: 0.0,
            file.getString("${location}.y")?.toDouble() ?: 0.0,
            file.getString("${location}.z")?.toDouble() ?: 0.0,
            file.getString("${location}.yaw")?.toFloat() ?: 0.0f,
            file.getString("${location}.pitch")?.toFloat() ?: 0.0f
        )

    }

}