package gg.crystalized.crystalizedBattleRoyale.display

import gg.crystalized.crystalizedBattleRoyale.CrystalizedBattleRoyale
import gg.crystalized.crystalizedBattleRoyale.Game
import gg.crystalized.crystalizedBattleRoyale.utils.VisualUtils
import org.bukkit.Bukkit
import org.bukkit.scoreboard.Criteria
import org.bukkit.scoreboard.DisplaySlot

class GameScoreboardManager(val game: Game) {

    val title = CrystalizedBattleRoyale.instance.config.getString("scoreboard.sidebar-title") ?: "title"
    private val manager = Bukkit.getScoreboardManager()
    private val scoreboard = manager.newScoreboard
    private val objective = scoreboard.registerNewObjective("game", Criteria.DUMMY, VisualUtils.parseToComponent(title))

    init {
        objective.displaySlot = DisplaySlot.SIDEBAR
        val players = game.players.size
        val required = game.minimumPlayers
        val time = game.curentTime
        //Here are the lines of the scoreboard
        val lines = listOf(
            "Battle Royal Lobby",
            "Players: ${players }/$required",
            "Time: $time."
        )

        for((index, text) in lines.withIndex()){
            val team = scoreboard.registerNewTeam("line$index")
            val entry = "§$index"
            team.addEntry(entry)
            team.prefix(VisualUtils.parseToComponent(text))
            objective.getScore(entry).score = lines.size - index
        }
    }

    fun update(lineId: String, newText: String){
        objective.scoreboard?.resetScores("game")

        val team = scoreboard.getTeam(lineId) ?: return

        team.prefix(VisualUtils.parseToComponent(newText))
        updateDisplay()

    }

    private fun updateDisplay(){
        game.players.forEach {
            it.scoreboard = scoreboard
        }
    }

    fun updateTime(){
        scoreboard.getTeam("line2")?.prefix(VisualUtils.parseToComponent("Time: ${game.curentTime}."))
        updateDisplay()
    }
    fun updatePlayers(){
        scoreboard.getTeam("line1")?.prefix(VisualUtils.parseToComponent("Players: ${game.players.size}/${game.minimumPlayers}"))
        updateDisplay()
    }

}