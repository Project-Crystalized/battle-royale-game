package gg.crystalized.crystalizedBattleRoyale.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import gg.crystalized.crystalizedBattleRoyale.CrystalizedBattleRoyale
import java.io.File

object DataManager {

    val gson = Gson()

    fun readChests(): List<TierChest>{
        val file = File(CrystalizedBattleRoyale.instance.dataFolder, "chests.json")
        if(!file.exists()) return emptyList()

        val json = file.readText()
        val type = object : TypeToken<List<TierChest>>() {}.type
        return gson.fromJson(json, type) ?: mutableListOf()

    }



}